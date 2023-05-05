package com.schoolfood.datamodel.cart

import android.app.AlertDialog
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.schoolfood.R
import com.schoolfood.datamodel.customize.CustomizeModel
import com.schoolfood.sources.Customizations
import com.schoolfood.sources.Foods
import com.schoolfood.ui.cart.CartFragment
import java.io.File


class CartViewHolder (cartAdapter: CartAdapter, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val adapter : CartAdapter = cartAdapter;

    fun bind(dataModel: CartFoodModel) {
        val label : TextView = itemView.findViewById(R.id.dish_name)
        label.text = dataModel.name

        val costLabel : TextView = itemView.findViewById(R.id.dish_cost)
        val deleteBtn : ImageButton = itemView.findViewById(R.id.remove)

        val image : ImageView = itemView.findViewById(R.id.dish_image)
        image.setImageResource(dataModel.image)
        image.contentDescription = dataModel.name + " Image"

        if (dataModel.name == "Empty") {
            costLabel.text = "Nothing's here! Order soon!"
            deleteBtn.visibility = View.GONE
            return
        }

        costLabel.text = "$" + String.format("%.2f", dataModel.price)

        deleteBtn.setOnClickListener {
            val path = adapter.context.filesDir
            val file = File(path, "cart.txt")
            if (!file.exists()) file.createNewFile()
            var lines = file.readLines()

            val alertDialogBuilder = AlertDialog.Builder(adapter.context)
            alertDialogBuilder.setTitle("Remove " + dataModel.name + " from your cart?")

            alertDialogBuilder.setPositiveButton(android.R.string.yes) { _, _ ->
                println(lines)
                lines = lines.filter { !it.startsWith(dataModel.id) }
                val text = lines.joinToString(System.lineSeparator())
                file.writeText(text)

                (adapter.fragment as CartFragment).calculatePrice(lines)

                Toast.makeText(adapter.context,
                    "Removed " + dataModel.name + "!", Toast.LENGTH_SHORT).show()
            }

            alertDialogBuilder.setNeutralButton("Cancel") { _, _ ->}
            alertDialogBuilder.show()
        }

    }
}