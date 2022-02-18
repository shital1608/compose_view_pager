package com.example.viewpagerdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.viewpagerdemo.ui.theme.ViewPagerDemoTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewPagerDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ViewPager()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewPagerDemoTheme {
        Greeting("Android")
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalPagerApi
@Composable
fun ViewPager(){
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val backgroundColor = remember { mutableStateOf(Color.White)}

    HorizontalPager(count = 10, state = pagerState) { page->
        // Our page content
        if(page ==1){
            backgroundColor.value = Color.LightGray
        }else{
            backgroundColor.value =  Color.White
        }
        Column(modifier = Modifier.fillMaxSize().background(color = backgroundColor.value)) {
            Text(
                text = "Page: $page",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(fontSize = 100.sp),
                textAlign = TextAlign.Center
            )

            Button(onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(1)
                }
            }) {
                Text(text = "Scroll to page 2")
            }
        }
    }
}