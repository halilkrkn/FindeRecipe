package com.halilkrkn.finderecipe.feature.presentation.main.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.SecureFlagPolicy
import com.halilkrkn.finderecipe.ui.theme.DarkMidnightBlue
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    showBottomSheet: MutableState<Boolean>,
    contentScreen: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    if (showBottomSheet.value) {
        ModalBottomSheet(
            modifier = Modifier
                .wrapContentWidth(),
            containerColor = FloralWhite,
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState,
            dragHandle = {
                BottomSheetDefaults.DragHandle(
                    modifier = Modifier.clickable {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet.value = false
                            }
                        }
                    },
                    color = DarkMidnightBlue,
                    shape = MaterialTheme.shapes.extraSmall,
                    width = 60.dp,
                    height = 3.dp
                )
            },
            properties = ModalBottomSheetDefaults.properties(
                securePolicy = SecureFlagPolicy.Inherit
            )
        ) {
            // Sheet content
            contentScreen()
        }
    }
}