package com.nhathuy.replyapp.appui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.nhathuy.replyapp.R
import com.nhathuy.replyapp.data.Email
import com.nhathuy.replyapp.data.MailboxType

@Composable
fun ReplyDetailsScreen(
    replyUiState: ReplyUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier=Modifier
){
    Box(modifier = modifier){
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.inverseOnSurface)
            .padding(top = dimensionResource(id = R.dimen.detail_card_list_padding_top))){
            
            item {
              ReplyDetailsScreenTopBar(
                  onBackPressed,
                  replyUiState,
                  Modifier
                      .fillMaxWidth()
                      .padding(
                          bottom = dimensionResource(
                              id =
                              R.dimen.detail_topbar_padding_bottom
                          )
                      )
              )  
                ReplyEmailDetailsCard(
                    email=replyUiState.currentSelectedEmail,
                    mailboxType= replyUiState.currentMailbox,
                    modifier = Modifier.padding(horizontal =  dimensionResource(id =
                    R.dimen.detail_card_outer_padding_horizontal))
                )
            }
        }
    }
}

@Composable
fun ReplyEmailDetailsCard(email: Email, mailboxType: MailboxType, modifier: Modifier) {
    val context = LocalContext.current
    val displayToast = {text:String ->
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
    }

    Card(modifier = modifier,
    colors = CardDefaults.cardColors(containerColor =MaterialTheme.colorScheme.surface)) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.detail_card_inner_padding))) {
            DetailsScreenHeader(email,Modifier.fillMaxWidth())


            Text(text = stringResource(id = email.subject),
            style=MaterialTheme.typography.bodyMedium,
            color=MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(
                top= dimensionResource(id = R.dimen.detail_content_padding_top),
                bottom = dimensionResource(id = R.dimen.detail_expanded_subject_body_spacing)
            ))

            Text(text = stringResource(id = email.body),
                style=MaterialTheme.typography.bodyLarge,
                color=MaterialTheme.colorScheme.onSurfaceVariant,
               )
            DetailsScreenButtonBar(mailboxType,displayToast)
        }

    }
}

@Composable
fun DetailsScreenButtonBar(mailboxType: MailboxType, displayToast: (String) -> Unit,modifier: Modifier=Modifier) {
    Box(modifier = modifier){
        when(mailboxType){
            MailboxType.Drafts ->
                ActionButton(text= stringResource(id = R.string.continue_composing),
                onButtonClicked= displayToast)

            MailboxType.Spam ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = dimensionResource(R.dimen.detail_button_bar_padding_vertical)
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.detail_button_bar_item_spacing)
                    ),
                ) {
                    ActionButton(
                        text = stringResource(id = R.string.move_to_inbox),
                        onButtonClicked = displayToast,
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        text = stringResource(id = R.string.delete),
                        onButtonClicked = displayToast,
                        containIrreversibleAction = true,
                        modifier = Modifier.weight(1f)
                    )
                }
            MailboxType.Sent, MailboxType.Inbox ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = dimensionResource(R.dimen.detail_button_bar_padding_vertical)
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.detail_button_bar_item_spacing)
                    ),
                ) {
                    ActionButton(
                        text = stringResource(id = R.string.reply),
                        onButtonClicked = displayToast,
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        text = stringResource(id = R.string.reply_all),
                        onButtonClicked = displayToast,
                        modifier = Modifier.weight(1f)
                    )
                }
        }
    }
}

@Composable
fun ActionButton(text: String, onButtonClicked: (String) -> Unit,modifier: Modifier=Modifier, containIrreversibleAction: Boolean =false) {
    Box(modifier=modifier){
        Button(onClick = { onButtonClicked(text) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.detail_action_button_padding_vertical)),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(containIrreversibleAction){
                MaterialTheme.colorScheme.onErrorContainer
            }
        else{
                MaterialTheme.colorScheme.primaryContainer
            }
        )) {
            
        }
    }
}

@Composable
fun DetailsScreenHeader(email: Email, modifier: Modifier = Modifier) {
    Row(modifier=modifier) {
        ReplyProfileImage(
            drawableResource = email.sender.avatar,
            description = stringResource(email.sender.firstName) + " "
                    + stringResource(email.sender.lastName),
            modifier = Modifier.size(
                dimensionResource(R.dimen.email_header_profile_size)
            ))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.email_header_content_padding_horizontal),
                    vertical = dimensionResource(R.dimen.email_header_content_padding_vertical)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(email.sender.firstName),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = stringResource(email.createdAt),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
fun ReplyDetailsScreenTopBar(onBackPressed: () -> Unit, replyUiState: ReplyUiState, modifier: Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
       IconButton(onClick = onBackPressed,
       modifier = Modifier
           .padding(horizontal = dimensionResource(id = R.dimen.detail_topbar_back_button_padding_horizontal))
           .background(MaterialTheme.colorScheme.surface, shape = CircleShape)) {
           Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.navigation_back) )
       } 
    }
    Row(horizontalArrangement = Arrangement.Center,
    modifier = Modifier
        .fillMaxWidth()
        .padding(end = dimensionResource(id = R.dimen.detail_subject_padding_end))) {
        Text(text = stringResource(id = replyUiState.currentSelectedEmail.subject),
        style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
