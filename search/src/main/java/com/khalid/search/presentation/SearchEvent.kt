package com.khalid.search.presentation

import com.khalid.core_ui.bases.BaseEvent

sealed class SearchEvent : BaseEvent {
    data class OnTextChanged(val newValue: String) : SearchEvent()
}