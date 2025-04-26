import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.example.realtimepopulation.ui.shared.viewmodel.DetailScreenViewModel
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun GenderDistributionChart(
    dtViewModel: DetailScreenViewModel,
    malePercentage: Float,
    femalePercentage: Float,
) {
    val chartUiModel by dtViewModel.chartUiModel.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(top = 15.dp)
    ) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .onSizeChanged {
                dtViewModel.calculateChart(
                    it.width.toFloat(), it.height.toFloat(), malePercentage, femalePercentage
                )
            }) {
            chartUiModel?.let { model ->
                drawArc(
                    color = Color(0xFF6365CF),
                    startAngle = 180f,
                    sweepAngle = model.maleAngle,
                    useCenter = true,
                    topLeft = Offset(
                        model.centerX - model.outerRadius, model.centerY - model.outerRadius
                    ),
                    size = Size(model.diameter, model.diameter)
                )

                drawArc(
                    color = Color(0xFFE9546B),
                    startAngle = 180f + model.maleAngle,
                    sweepAngle = model.femaleAngle,
                    useCenter = true,
                    topLeft = Offset(
                        model.centerX - model.outerRadius, model.centerY - model.outerRadius
                    ),
                    size = Size(model.diameter, model.diameter)
                )

                val innerCirclePath = Path().apply {
                    addOval(
                        Rect(
                            model.centerX - model.innerRadius,
                            model.centerY - model.innerRadius,
                            model.centerX + model.innerRadius,
                            model.centerY + model.innerRadius
                        )
                    )
                }

                clipPath(innerCirclePath) {
                    drawRect(
                        color = Color.White,
                        topLeft = Offset(0f, 0f),
                        size = Size(size.width, size.height)
                    )
                }
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Canvas(modifier = Modifier.size(12.dp)) {
                drawCircle(color = Color(0xFF6365CF))
            }
            Text("남성", fontSize = 14.sp, color = Color.DarkGray)
            Text("${malePercentage}%", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6365CF))
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Canvas(modifier = Modifier.size(12.dp)) {
                drawCircle(color = Color(0xFFE9546B))
            }
            Text("여성", fontSize = 14.sp, color = Color.DarkGray)
            Text("${femalePercentage}%", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE9546B))
        }
    }
}