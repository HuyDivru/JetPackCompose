package com.nhathuy.replyapp.appui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nhathuy.replyapp.R
import com.nhathuy.replyapp.data.Email
import com.nhathuy.replyapp.data.MailboxType
import com.nhathuy.replyapp.ui.theme.ReplyAppTheme

@Composable
fun ReplyHomeScreen(
    replyUiState: ReplyUiState,
    onTabPressed: (MailboxType) -> Unit,
    onEmailCardPressed : (Email) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    modifier: Modifier= Modifier
){
    val navigationItemContentList = listOf(
        NavigationItemContent(
            mailboxType = MailboxType.Inbox,
            icon = Icons.Default.Inbox,
            text = stringResource(id = R.string.tab_inbox)
        ),
        NavigationItemContent(
            mailboxType = MailboxType.Sent,
            icon = Icons.Default.Send,
            text = stringResource(id = R.string.tab_sent)
        ),
        NavigationItemContent(
            mailboxType = MailboxType.Drafts,
            icon = Icons.Default.Drafts,
            text = stringResource(id = R.string.tab_drafts)
        ),
        NavigationItemContent(
            mailboxType = MailboxType.Spam,
            icon = Icons.Default.Report,
            text = stringResource(id = R.string.tab_spam)
        ),
    )
    ReplyAppContent(
        replyUiState=replyUiState,
        onTabPressed=onTabPressed,
        onEmailCardPressed=onEmailCardPressed,
        navigationItemContentList= navigationItemContentList,
        modifier=modifier
    )
}

@Composable
fun ReplyAppContent(replyUiState: ReplyUiState,
                    onTabPressed: (MailboxType) ->
                    Unit, onEmailCardPressed: (Email) -> Unit,
                    navigationItemContentList: List<NavigationItemContent>, modifier: Modifier) {
    
    Box(modifier = modifier){
        val navigationRailContentDescription= stringResource(id = R.string.navigation_rail)
        ReplyNavigationRail(
            currentTab= replyUiState.currentMailbox,
            onTabPressed = onTabPressed,
            navigationItemContentList = navigationItemContentList,
            modifier =Modifier.testTag(navigationRailContentDescription)
        )
        Column(modifier= Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseOnSurface)) {
            ReplyListOnlyContent(
                replyUiState=replyUiState,
                onEmailCardPressed=onEmailCardPressed,
                modifier= Modifier
                    .weight(1f)
                    .padding(horizontal = dimensionResource(id = R.dimen.email_list_only_horizontal_padding))
            )
        }
    }
}


@Composable
fun ReplyNavigationRail(currentTab: MailboxType, onTabPressed: (MailboxType) -> Unit, navigationItemContentList: List<NavigationItemContent>, modifier: Modifier) {
    NavigationRail(modifier= modifier) {
        for (navItem in navigationItemContentList){
            NavigationRailItem(selected = currentTab==navItem.mailboxType, onClick = { onTabPressed(navItem.mailboxType) },
            icon = {
                Icon(imageVector = navItem.icon, contentDescription =navItem.text)
            })
        }
    }
}

data class NavigationItemContent(
    val mailboxType: MailboxType,
    val icon:ImageVector,
    val text:String
)



@Preview
@Composable
fun HomeScreenPreview(){
    ReplyAppTheme {
//        ReplyHomeScreen(
//            replyUiState = ,
//            onTabPressed = ,
//            onEmailCardPressed = ,
//            onDetailScreenBackPressed = { /*TODO*/ })
    }
}