package com.example.expertcoursequizgame.views.visiblebutton

import java.io.Serializable

data class VisibilityUiState(private val visibility: Int) : Serializable {
    fun update(updateVisibility: UpdateVisibility) = updateVisibility.update(visibility)
}