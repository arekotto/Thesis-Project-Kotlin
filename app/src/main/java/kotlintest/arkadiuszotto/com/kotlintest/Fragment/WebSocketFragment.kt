package kotlintest.arkadiuszotto.com.kotlintest.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import kotlintest.arkadiuszotto.com.kotlintest.R
import okhttp3.*


/**
 * A simple [Fragment] subclass.
 */
class WebSocketFragment : Fragment() {

    companion object {
        private const val NORMAL_CLOSURE_STATUS = 1000
    }

    private inner class EchoWebSocketListener : WebSocketListener() {
        internal var isOpen = false

        override fun onOpen(webSocket: WebSocket?, response: Response?) {
            isOpen = true
            activity?.runOnUiThread {
                connectionButton.setText(R.string.fragment_web_socket_button_connection_disconnect)
            }

        }

        override fun onMessage(webSocket: WebSocket?, text: String?) {
            activity?.runOnUiThread {
                receivedMessagesTextView.append(text + '\n')
            }
        }

        override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
            isOpen = false
            activity?.runOnUiThread {
                connectionButton.setText(R.string.fragment_web_socket_button_connection_connect)
            }
        }
    }

    private lateinit var receivedMessagesTextView: TextView
    private lateinit var messageTextField: EditText
    private lateinit var connectionButton: Button

    private val client = OkHttpClient()
    private lateinit var webSocket: WebSocket
    private val webSocketListener = EchoWebSocketListener()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_web_socket, container, false)

        receivedMessagesTextView = inflate.findViewById(R.id.text_view_received_messages)
        receivedMessagesTextView.movementMethod = ScrollingMovementMethod()

        messageTextField = inflate.findViewById(R.id.text_field_message)

        inflate.findViewById<Button>(R.id.button_send_message).setOnClickListener {
            if (webSocketListener.isOpen) {
                webSocket.send(messageTextField.text.toString())
            } else {
                showNoConnectionToast()
            }
        }

        inflate.findViewById<Button>(R.id.button_clear).setOnClickListener {
            receivedMessagesTextView.text = ""
        }

        connectionButton = inflate.findViewById(R.id.button_connection)
        connectionButton.setOnClickListener { connectionButtonTapped() }
        connectionButton.setText(R.string.fragment_web_socket_button_connection_connect)
        return inflate
    }

    private fun connectionButtonTapped() {
        if (webSocketListener.isOpen) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
        } else {
            val request = Request.Builder().url("ws://echo.websocket.org").build()
            webSocket = client.newWebSocket(request, webSocketListener)
        }
    }
}

fun Fragment.showNoConnectionToast() {
    activity?.applicationContext.let {
        Toast.makeText(it, R.string.fragment_web_socket_no_connection, Toast.LENGTH_SHORT).show()
    }
}