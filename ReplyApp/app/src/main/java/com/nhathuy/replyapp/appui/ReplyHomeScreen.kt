package com.nhathuy.replyapp.appui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
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
            val bottomNavigationContentDescription = stringResource(id = R.string.navigation_bottom)
            ReplyBottomNavigationBar(
                currentTab= replyUiState.currentMailbox,
                onTabPressed = onTabPressed,
                navigationItemContentList = navigationItemContentList,
                modifier= Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ReplyBottomNavigationBar(currentTab: MailboxType, onTabPressed: (MailboxType) -> Unit, navigationItemContentList: List<NavigationItemContent>, modifier: Modifier) {
    NavigationBar(modifier=modifier){
        for(navItem in navigationItemContentList){
            NavigationBarItem(selected = currentTab==navItem.mailboxType
                , onClick = {onTabPressed(navItem.mailboxType) },
            icon = {
                Icon(imageVector = navItem.icon, contentDescription = navItem.text)
            }) 
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

