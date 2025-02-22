package com.codingWithUmair.pieChart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(
	colorAndDataPercentageMap: Map<Color, Float>,
	canvasSize: Dp = 100.dp,
	startAngle: Float = 270f,
	drawStyle: DrawStyle = Stroke(
		width = 12f,
		cap = StrokeCap.Round
	),
	joinPathToCenter: Boolean = false,
	gapBetweenEachPie: Float = 12f,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
			.size(canvasSize)
			.drawBehind {
				val totalPercentage = colorAndDataPercentageMap.values.sum()
				var angle = startAngle
				colorAndDataPercentageMap.forEach { (color, percentage) ->
					val sweepAngle = if(colorAndDataPercentageMap.size > 1){
							((percentage / totalPercentage) * 360f) - gapBetweenEachPie
					}else (percentage / totalPercentage) * 360f
					drawArc(
						color = color,
						startAngle = angle,
						sweepAngle = sweepAngle,
						useCenter = joinPathToCenter,
						style = drawStyle
					)
					angle += sweepAngle + gapBetweenEachPie
				}
			}
	)
}
