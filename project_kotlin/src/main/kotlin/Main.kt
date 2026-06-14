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
import game.GameState
import models.ShipClass

/**
 * 게임의 메인 UI를 그리는 최상위 Composable 함수입니다.
 * Compose UI는 상태(State)가 변경될 때마다 화면을 자동으로 다시 그립니다(Recomposition).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(gameManager: GameManager) {
    // GameManager의 StateFlow 데이터들을 Compose에서 사용할 수 있도록 관찰(collectAsState)합니다.
    // 값이 변경될 때마다 이 변수를 참조하는 UI 요소들만 똑똑하게 업데이트됩니다.
    val logs by gameManager.logs.collectAsState()
    val gameState by gameManager.gameState.collectAsState()
    val discoveredPlanets by gameManager.discoveredPlanets.collectAsState()
    val listState = rememberLazyListState()

    // 코루틴 스코프를 제공하는 LaunchedEffect를 활용하여 로그가 추가될 때마다 최하단으로 자동 스크롤시킵니다.
    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.size - 1)
        }
    }

    // Material 3 디자인 시스템 적용 (어두운 테마)
    MaterialTheme(colorScheme = darkColorScheme()) {
        // sealed class를 활용한 상태 분기
        if (gameState is GameState.CharacterCreation) {
            CharacterCreationScreen(gameManager)
        } else {
            // 메인 화면 가로 분할 레이아웃
            Row(modifier = Modifier.fillMaxSize().background(Color(0xFF121212)).padding(16.dp)) {
                
                // --- 좌측 패널 (내 함선 정보, 인벤토리, 조작 패널) ---
                Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    Text("🚀 우주 탐사선 시뮬레이터 RPG", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // 1. 내 함선 정보 패널
                    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // GameManager의 myShip 객체의 속성들을 화면에 바인딩
                            Text("함선: ${gameManager.myShip.name} (Lv.${gameManager.myShip.level} ${gameManager.myShip.shipClass.className})", color = Color.LightGray)
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            // 체력 바
                            Text("체력 (HP): ${gameManager.myShip.hp}/${gameManager.myShip.maxHp}", color = Color(0xFFFF5252))
                            LinearProgressIndicator(
                                progress = { (gameManager.myShip.hp.coerceAtLeast(0).toFloat() / gameManager.myShip.maxHp.toFloat()) },
                                modifier = Modifier.fillMaxWidth().height(8.dp),
                                color = Color(0xFFFF5252)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            // 경험치 바
                            Text("경험치: ${gameManager.myShip.exp}/${gameManager.myShip.level * 30}", color = Color(0xFFB388FF))
                            LinearProgressIndicator(
                                progress = { (gameManager.myShip.exp.toFloat() / (gameManager.myShip.level * 30).toFloat()).coerceIn(0f, 1f) },
                                modifier = Modifier.fillMaxWidth().height(8.dp),
                                color = Color(0xFFB388FF)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            // 자원 상태
                            Text("연료: ${gameManager.myShip.fuel}", color = Color(0xFF4CAF50))
                            Text("광물: ${gameManager.myShip.minerals}", color = Color(0xFF2196F3))
                            Text("공격력: ${gameManager.myShip.attackPower}", color = Color.Yellow)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 2. 인벤토리 (Inventory) 패널
                    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFF2E2E2E))) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("🎒 인벤토리", color = Color.White, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            // 인벤토리가 완전히 비었거나 모두 0개일 경우 처리
                            if (gameManager.myShip.inventory.isEmpty() || gameManager.myShip.inventory.values.all { it == 0 }) {
                                Text("아이템이 없습니다.", color = Color.Gray, fontSize = 12.sp)
                            } else {
                                // 인벤토리 순회 (Collection 연산 적용)
                                gameManager.myShip.inventory.forEach { (itemName, count) ->
                                    if (count > 0) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text("$itemName x$count", color = Color.White, fontSize = 14.sp)
                                            Button(
                                                onClick = { gameManager.useItem(itemName) },
                                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                                modifier = Modifier.height(28.dp)
                                            ) {
                                                Text("사용", fontSize = 12.sp)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // 3. 조작 버튼 영역
                    Button(
                        onClick = { gameManager.scanSector() }, 
                        modifier = Modifier.fillMaxWidth(),
                        // 현재 상태가 Exploring 일 때만 스캔 버튼을 누를 수 있도록 제어
                        enabled = gameState is GameState.Exploring
                    ) {
                        Text("우주 섹터 스캔")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { gameManager.saveGame() }, modifier = Modifier.fillMaxWidth()) {
                        Text("게임 저장")
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // --- 우측 패널 (메인 뷰포트 & 로그창) ---
                Column(modifier = Modifier.weight(2f).fillMaxHeight()) {
                    
                    // 상단: 메인 게임 뷰포트 (상태에 따라 화면이 180도 바뀜)
                    Box(
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxWidth()
                            .background(if (gameState is GameState.Combat) Color(0xFF3E1212) else Color(0xFF0A0A1A)),
                        contentAlignment = Alignment.Center
                    ) {
                        when (val state = gameState) {
                            is GameState.Exploring -> {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("🌌 평화로운 우주 탐사 중...", color = Color.Cyan, fontSize = 20.sp)
                                    Spacer(modifier = Modifier.height(16.dp))
                                    if (discoveredPlanets.isNotEmpty()) {
                                        Text("발견한 행성 목록", color = Color.White, fontWeight = FontWeight.Bold)
                                        LazyColumn(modifier = Modifier.height(100.dp)) {
                                            items(discoveredPlanets.toList()) { planet ->
                                                Text("- $planet", color = Color.LightGray)
                                            }
                                        }
                                    }
                                }
                            }
                            is GameState.Combat -> {
                                // 전투 모드 (적의 정보와 내 공격/도망 버튼이 노출)
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("🚨 전투 발생: ${state.enemy.name}", color = Color.Red, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(16.dp))
                                    
                                    Text("적 HP: ${state.enemy.hp}", color = Color.White)
                                    LinearProgressIndicator(
                                        progress = { state.enemy.hp.coerceAtLeast(0).toFloat() / state.enemy.maxHp.toFloat() },
                                        modifier = Modifier.width(200.dp).height(12.dp),
                                        color = Color.Red
                                    )
                                    
                                    Spacer(modifier = Modifier.height(24.dp))
                                    
                                    Row {
                                        Button(onClick = { gameManager.attackEnemy() }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                                            Text("⚔️ 공격 (Damage: ${gameManager.myShip.attackPower})", color = Color.White)
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Button(onClick = { gameManager.flee() }, colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)) {
                                            Text("💨 도망가기 (연료 -10)", color = Color.White)
                                        }
                                    }
                                }
                            }
                            is GameState.GameOver -> {
                                Text("💀 GAME OVER", color = Color.Red, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                            }
                            else -> {}
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 하단: 시스템 로그 (LazyColumn을 사용하여 스크롤 가능한 대량의 리스트 최적화 구현)
                    Card(modifier = Modifier.weight(1f).fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.Black)) {
                        LazyColumn(state = listState, modifier = Modifier.padding(12.dp).fillMaxSize()) {
                            items(logs) { logMsg ->
                                Text("> $logMsg", color = Color(0xFF00FF00), fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace, fontSize = 13.sp)
                                Spacer(modifier = Modifier.height(2.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 캐릭터(함선) 생성 UI 컴포넌트입니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCreationScreen(gameManager: GameManager) {
    // 텍스트 필드와 라디오 버튼의 상태를 기억(remember)합니다.
    var shipName by remember { mutableStateOf("") }
    var selectedClass by remember { mutableStateOf(ShipClass.EXPLORER) }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF121212)), contentAlignment = Alignment.Center) {
        Card(modifier = Modifier.width(400.dp).padding(16.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("새로운 함선 등록", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(24.dp))
                
                // 이름 입력 텍스트 필드
                OutlinedTextField(
                    value = shipName,
                    onValueChange = { shipName = it },
                    label = { Text("함선 이름", color = Color.LightGray) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                Text("함선 클래스 선택", color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                
                // Enum 값을 순회하며 라디오 버튼 동적 생성
                ShipClass.values().forEach { shipClass ->
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                        RadioButton(
                            selected = (selectedClass == shipClass),
                            onClick = { selectedClass = shipClass },
                            colors = RadioButtonDefaults.colors(selectedColor = Color.Cyan)
                        )
                        Text(
                            text = "${shipClass.className} (HP: ${shipClass.baseHp}, ATK: ${shipClass.baseAttack})",
                            color = Color.White
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                // 이름이 비어있으면 시작 버튼 비활성화 처리
                Button(
                    onClick = { gameManager.createCharacter(shipName, selectedClass) },
                    enabled = shipName.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("탐사 시작")
                }
            }
        }
    }
}

/**
 * 애플리케이션의 진입점(Entry Point)입니다.
 */
fun main() = application {
    // GameManager 인스턴스를 하나 생성하여 전체 앱에 공급합니다.
    val gameManager = remember { GameManager() }
    
    // 앱이 시작될 때 단 한 번 실행되는 백그라운드 코루틴 작업 (자동 채굴 시작)
    LaunchedEffect(Unit) {
        gameManager.startBackgroundMining()
    }

    Window(onCloseRequest = ::exitApplication, title = "Space Explorer RPG v2.0") {
        App(gameManager)
    }
}
