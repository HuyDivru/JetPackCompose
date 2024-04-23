package com.nhathuy.replyapp.appui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nhathuy.replyapp.data.Email
import com.nhathuy.replyapp.data.MailboxType

@Composable
fun ReplyApp(modifier: Modifier=Modifier){
    val viewModel:ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    ReplyHomeScreen(
        replyUiState = replyUiState,
        onTabPressed = {mailboxType :MailboxType ->
            viewModel.updateCurrentMailbox(mailboxType=mailboxType)
            viewModel.resetHomeScreenStates()
        },
        onEmailCardPressed ={
                            email : Email ->
            viewModel.updateDetailScreenStates(email=email)
        },
        onDetailScreenBackPressed = {
            viewModel.resetHomeScreenStates()
        },
    modifier = modifier)
}