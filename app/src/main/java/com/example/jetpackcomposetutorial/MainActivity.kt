package com.example.jetpackcomposetutorial

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetutorial.ui.theme.SampleTutorialTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            SampleTutorialTheme {
                Conversation(SampleData.conversationSample)
            }
        }
    }

    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember(msg.body) { mutableStateOf(false)}
            val surfaceColor by animateColorAsState(
                if(isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                )
                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = surfaceColor,
                    elevation = 1.dp,
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(msg = message)
            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        name = "Dark Mode",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true
    )
    @Composable
    fun PreviewConversation() {
        SampleTutorialTheme {
            Surface {
                Conversation(SampleData.conversationSample)
            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        name = "Dark Mode",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true
    )
    @Composable
    fun PreviewMessageCard() {
        SampleTutorialTheme {
            Surface {
                MessageCard(msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!"))
            }
        }
    }

    object SampleData {
        // Sample conversation data
        val conversationSample = listOf(
            Message(
                "Colleague",
                "Test...Test...Test..."
            ),
            Message(
                "Colleague",
                "List of Android versions:\n" +
                        "Android KitKat (API 19)\n" +
                        "Android Lollipop (API 21)\n" +
                        "Android Marshmallow (API 23)\n" +
                        "Android Nougat (API 24)\n" +
                        "Android Oreo (API 26)\n" +
                        "Android Pie (API 28)\n" +
                        "Android 10 (API 29)\n" +
                        "Android 11 (API 30)\n" +
                        "Android 12 (API 31)\n"
            ),
            Message(
                "Colleague",
                "I think Kotlin is my favorite programming language.\n" +
                        "It's so much fun!"
            ),
            Message(
                "Colleague",
                "Searching for alternatives to XML layouts..."
            ),
            Message(
                "Colleague",
                "Hey, take a look at Jetpack Compose, it's great!\n" +
                        "It's the Android's modern toolkit for building native UI." +
                        "It simplifies and accelerates UI development on Android." +
                        "Less code, powerful tools, and intuitive Kotlin APIs :)"
            ),
            Message(
                "Colleague",
                "It's available from API 21+ :)"
            ),
            Message(
                "Colleague",
                "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
            ),
            Message(
                "Colleague",
                "Android Studio next version's name is Arctic Fox"
            ),
            Message(
                "Colleague",
                "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
            ),
            Message(
                "Colleague",
                "I didn't know you can now run the emulator directly from Android Studio"
            ),
            Message(
                "Colleague",
                "Compose Previews are great to check quickly how a composable layout looks like"
            ),
            Message(
                "Colleague",
                "Previews are also interactive after enabling the experimental setting"
            ),
            Message(
                "Colleague",
                "Have you tried writing build.gradle with KTS?"
            ),
        )
    }
}