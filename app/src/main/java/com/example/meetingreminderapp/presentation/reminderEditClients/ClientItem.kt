package com.example.meetingreminderapp.presentation.reminderEditClients

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meetingreminderapp.domain.models.UserData
import com.example.meetingreminderapp.domain.models.UserName
import com.example.meetingreminderapp.domain.models.UserPicture
import com.example.meetingreminderapp.presentation.reminderList.LoadingImage

@Composable
fun CLientItem(
    userData: UserData,
    modifier: Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
            ) {
                LoadingImage(imgUrl = userData.picture.medium)
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ФИО:",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${userData.name.last} ${userData.name.first} ${userData.name.title}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Email:",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = userData.email,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}