package com.kyrgyzbilim.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.OnItemClickListener
import com.kyrgyzbilim.data.remote.subTopic.SubTopic
import kotlinx.android.synthetic.main.item_dialog.view.*
import java.lang.Exception

class DialogVocabularyAdapter :
    ListAdapter<SubTopic, DialogVocabularyAdapter.SubTopicViewHolder>(DIFF) {

    var context: Context? = null

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<SubTopic>() {
            override fun areItemsTheSame(oldItem: SubTopic, newItem: SubTopic): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SubTopic, newItem: SubTopic): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setData(context: Context?) {
        this.context = context
    }

    inner class SubTopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("ResourceAsColor", "ClickableViewAccessibility")
        fun onBind(position: Int) {
            val currentSection = getItem(position)

            itemView.dialog.text = currentSection.text
            itemView.translationDialog.text = currentSection.translated_text

            var event = false
            var mediaPlayer: MediaPlayer? = null
            itemView.listenAudio.setOnClickListener {
                try {
                    if (!currentSection.audio.isNullOrEmpty()) {
                        event = true
                        val myUri: Uri = Uri.parse(currentSection.audio)
                        if (mediaPlayer == null) {
                            mediaPlayer = MediaPlayer().apply {
                                setAudioAttributes(
                                    AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                        .setUsage(AudioAttributes.USAGE_MEDIA)
                                        .build()
                                )
                                context?.let { it1 -> setDataSource(it1, myUri) }
                            }
                            mediaPlayer?.prepare()
                        }
                        event = if (event) {
                            mediaPlayer?.start()
                            mediaPlayer?.setOnCompletionListener {
                                event = true
                            }
                            false
                        } else {
                            mediaPlayer?.pause()
                            mediaPlayer?.seekTo(0)
                            true
                        }
                    }
                } catch (e: Exception) {
                    Log.d(
                        "Dialog Voc Adapter",
                        "Most probably there is no file uploaded on the server"
                    )
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicViewHolder {
        return SubTopicViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_dialog, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubTopicViewHolder, position: Int) {
        holder.onBind(position)
    }
}
