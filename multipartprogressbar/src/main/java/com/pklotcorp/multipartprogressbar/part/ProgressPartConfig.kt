package com.pklotcorp.multipartprogressbar.part

data class ProgressPartConfig(val progressPartColors: List<ProgressPartColor>) {

    companion object {
        fun create(progressParts: List<ProgressPart>): ProgressPartConfig {
            val maxValue = progressParts.map { it.getPartValue() }.sum()
            val progressColors = progressParts.mapIndexed { index, progressPart ->
                if (index > 0) {
                    val previousPart = progressParts[index - 1]
                    check(previousPart.maxValue() == progressPart.minValue()) { "value not continuous" }
                }
                ProgressPartColor(
                    progressPart.minValue() / maxValue.toFloat(),
                    progressPart.maxValue() / maxValue.toFloat(),
                    progressPart.startColor(),
                    progressPart.endColor()
                )
            }
            return ProgressPartConfig(progressColors)
        }
    }
}