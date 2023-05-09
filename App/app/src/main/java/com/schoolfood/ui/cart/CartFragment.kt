package com.schoolfood.ui.cart

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.schoolfood.MainActivity
import com.schoolfood.R
import com.schoolfood.api.GetBalance
import com.schoolfood.api.PlaceOrder
import com.schoolfood.databinding.FragmentCartBinding
import com.schoolfood.datamodel.cart.CartAdapter
import com.schoolfood.datamodel.cart.CartFoodModel
import com.schoolfood.sources.Foods
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream

class CartFragment : Fragment() {

    private val dataAdapter: CartAdapter by lazy {
        CartAdapter(this, activity as MainActivity)
    }

    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        calculatePrice()

        val mainView = binding.cartRecyclerView
        mainView.apply {
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
            this.adapter = dataAdapter
        }

        binding.cartConfirm.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("Confirm your Purchase?")
            alertDialogBuilder.setMessage("You cannot cancel your order after it has been placed.")

            alertDialogBuilder.setPositiveButton(android.R.string.yes) { _, _ ->
                val path = context?.filesDir
                val file = File(path, "cart.txt")
                if (!file.exists()) file.createNewFile()
                val lines = FileInputStream(file).bufferedReader().use { it.readLines() }
                val arr = JSONArray()
                for (line in lines) {
                    val args = line.split(",")
                    if(args.size < 4) continue

                    var obj = JSONObject()
                    obj.put("name", args[1])
                    obj.put("customizations", JSONArray(args.drop(3)))

                    arr.put(obj)
                }
                file.writeText("")

                val file2 = File(path, "timesOrdered.txt")
                if (!file2.exists()) file2.createNewFile()
                val timesOrdered = FileInputStream(file2).bufferedReader().use { it.readLines() }
                var times = if (timesOrdered.isEmpty()) 0 else timesOrdered[0].toInt()
                file2.writeText((times + 1).toString())

                val threadWithRunnable = Thread(PlaceOrder(activity as MainActivity, arr.toString()))
                threadWithRunnable.start()

                calculatePrice()
            }

            alertDialogBuilder.setNeutralButton(android.R.string.cancel) { _, _ -> }

            alertDialogBuilder.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun calculatePrice(lines : List<String> = listOf()) {
        var price = 0.0
        var linesCopy = lines

        if (linesCopy.isEmpty()) {
            val path = context?.filesDir
            val file = File(path, "cart.txt")
            if (!file.exists()) file.createNewFile()
            linesCopy = FileInputStream(file).bufferedReader().use { it.readLines() }

        }

        if (linesCopy.isEmpty()) {
            dataAdapter.setData(
                listOf(
                    CartFoodModel(
                        id = "",
                        image = R.drawable.baseline_question_mark_24,
                        name = "Empty",
                        price = 0F,
                        restaurant = "",
                        customizations = listOf()
                    )
                )
            )
            binding.cartConfirm.isEnabled = false
        } else {
            var listOfFoods = mutableListOf<CartFoodModel>()
            for (line in linesCopy) {
                val args = line.split(",")
                if (args.size < 4) continue
                val dish = Foods.getFoods().find { it.name == args[1] }
                if (dish != null) {
                    val cartFoodModel = CartFoodModel(
                        id = args[0],
                        restaurant = dish.restaurant,
                        name = dish.name,
                        price = args[2].toFloat(),
                        image = dish.image,
                        customizations = args
                    )
                    price += cartFoodModel.price
                    listOfFoods.add(cartFoodModel)
                }
            }
            if (listOfFoods.isEmpty()) {
                // clear it - something is wrong!
                val path = context?.filesDir
                val file = File(path, "cart.txt")
                file.writeText("")
                listOfFoods.add(
                    CartFoodModel(
                        id = "",
                        image = R.drawable.baseline_question_mark_24,
                        name = "Empty",
                        price = 0F,
                        restaurant = "",
                        customizations = listOf()
                    )
                )
                binding.cartConfirm.isEnabled = false
            } else {
                binding.cartConfirm.isEnabled = true
            }
            dataAdapter.setData(listOfFoods)
        }

        val priceView = binding.dishCost
        priceView.text = "$" + String.format("%.2f", price)
    }
}
