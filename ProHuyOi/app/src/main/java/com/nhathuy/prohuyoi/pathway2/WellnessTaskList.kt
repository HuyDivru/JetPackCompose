package com.nhathuy.prohuyoi.pathway2

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WellnessTaskList(
    list: List<WellnessTask>,
    onCheckedTask:(WellnessTask,Boolean) ->Unit,
    onCloseTask:(WellnessTask) ->Unit,
    modifier: Modifier=Modifier
) {
    LazyColumn(modifier = modifier){
        items(items = list , key = {task -> task.id}){
            task ->
            WellnessItem(taskName= task.label,
            checked= task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
            onCloseTask={onCloseTask(task)})
        }
    }

}

@Composable
fun WellnessItem(taskName: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit, onCloseTask: () -> Unit,modifier: Modifier=Modifier) {
    Row(modifier=modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = taskName, modifier = modifier
            .weight(1f)
            .padding(start = 16.dp))
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = onCloseTask) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
        }
    }
}


