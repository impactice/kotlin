import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import game.GameManager

@Composable
@Preview
fun App(gameManager: GameManager) {
    val logs by gameManager.logs.collectAsState()
    val listState = rememberLazyListState()

    // 로그가 추가될 때마다 맨 아래로 자동 스크롤
    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.size - 1)
        }
    }

    MaterialTheme(colorScheme = darkColorScheme()) {
        Row(modifier = Modifier.fillMaxSize().background(Color(0xFF121212)).padding(16.dp)) {
            // 왼쪽 패널 (상태 및 조작)
            Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                Text("🚀 우주 탐사선 시뮬레이터", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("함선 이름: ${gameManager.myShip.name}", color = Color.LightGray)
                        Text("연료: ${gameManager.myShip.fuel}", color = Color(0xFF4CAF50))
                        Text("광물: ${gameManager.myShip.minerals}", color = Color(0xFF2196F3))
                        Text("승무원: ${gameManager.myShip.crew.joinToString(", ")}", color = Color.LightGray)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                
                Button(onClick = { gameManager.scanSector() }, modifier = Modifier.fillMaxWidth()) {
                    Text("우주 섹터 스캔")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { gameManager.saveGame() }, modifier = Modifier.fillMaxWidth()) {
                    Text("게임 저장")
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 오른쪽 패널 (로그 화면)
            Card(modifier = Modifier.weight(1.5f).fillMaxHeight(), colors = CardDefaults.cardColors(containerColor = Color.Black)) {
                LazyColumn(state = listState, modifier = Modifier.padding(16.dp).fillMaxSize()) {
                    items(logs) { logMsg ->
                        Text("> $logMsg", color = Color(0xFF00FF00), fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

fun main() = application {
    val gameManager = remember { GameManager() }
    
    LaunchedEffect(Unit) {
        gameManager.startBackgroundMining()
    }

    Window(onCloseRequest = ::exitApplication, title = "Space Explorer GUI") {
        App(gameManager)
    }
}
