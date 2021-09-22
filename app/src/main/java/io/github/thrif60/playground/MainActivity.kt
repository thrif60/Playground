package io.github.thrif60.playground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.thrif60.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val activityList = listOf(
        ActivityModel("메인", MainActivity::class.java.simpleName, MainActivity::class.java),
        ActivityModel("메인", MainActivity::class.java.simpleName, MainActivity::class.java),
        ActivityModel("메인", MainActivity::class.java.simpleName, MainActivity::class.java),
        ActivityModel("메인", MainActivity::class.java.simpleName, MainActivity::class.java),
        ActivityModel("메인", MainActivity::class.java.simpleName, MainActivity::class.java),
        ActivityModel("메인", MainActivity::class.java.simpleName, MainActivity::class.java),
        ActivityModel("메인", MainActivity::class.java.simpleName, MainActivity::class.java),
        ActivityModel("메인", MainActivity::class.java.simpleName, MainActivity::class.java),
        ActivityModel("메인", MainActivity::class.java.simpleName, MainActivity::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.activityRecyclerView.adapter = ActivityAdapter(activityList)
        binding.activityRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    class ActivityModel(val title: String, val description: String, val cls: Class<*>)
    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ActivityAdapter(private val activityList: List<ActivityModel>) :
        RecyclerView.Adapter<ActivityViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
            return ActivityViewHolder(view)
        }

        override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
            val titleText = holder.itemView.findViewById<TextView>(R.id.titleText).apply {
                this.text = activityList[position].title
            }
            val descriptionText= holder.itemView.findViewById<TextView>(R.id.descriptionText).apply {
                this.text = activityList[position].description
            }
            holder.itemView.findViewById<Button>(R.id.activityBtn).setOnClickListener {
                val intent = Intent(holder.itemView.context, activityList[position].cls)
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return activityList.size
        }
    }
}