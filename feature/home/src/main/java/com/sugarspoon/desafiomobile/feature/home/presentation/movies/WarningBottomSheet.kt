package com.sugarspoon.desafiomobile.feature.home.presentation.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sugarspoon.desafiomobile.ds.theme.RedPrimary
import com.sugarspoon.desafiomobile.ds.theme.SurfaceDark
import com.sugarspoon.desafiomobile.ds.theme.TextFaded
import com.sugarspoon.desafiomobile.ds.theme.YellowPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarningBottomSheet(
    title: String = "Sem conexão com a internet",
    description: String = "Verifique sua rede e tente novamente.",
    onDismiss: () -> Unit,
    onRetry: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = SurfaceDark,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = YellowPrimary,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextFaded
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    onDismiss()
                    onRetry()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = RedPrimary)
            ) {
                Text("Tentar Novamente")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}