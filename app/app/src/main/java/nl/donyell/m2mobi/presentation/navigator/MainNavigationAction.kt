package nl.donyell.m2mobi.presentation.navigator

import nl.donyell.m2mobi.domain.models.Photo

sealed class MainNavigationAction {

    data class OpenDetail(
        val photo: Photo
    ) : MainNavigationAction()
}
