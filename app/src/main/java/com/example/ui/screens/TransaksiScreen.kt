package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TransactionHistoryItem
import com.example.ui.components.RecentTransactions
import com.example.ui.theme.BackgroundLight
import com.example.ui.theme.IceBlueCard
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftBlueBorder
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransaksiScreen(
    transactions: List<TransactionHistoryItem>,
    onItemClick: (TransactionHistoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf("Semua") }
    val filters = listOf("Semua", "CRYPTO", "PPOB", "TOPUP")

    val filteredList = remember(selectedFilter, transactions) {
        when (selectedFilter) {
            "CRYPTO" -> transactions.filter { it.category == "CRYPTO" }
            "PPOB" -> transactions.filter { it.category == "PLN" || it.category == "PULSA" || it.category == "TAGIHAN" }
            "TOPUP" -> transactions.filter { it.category == "TOPUP" }
            else -> transactions
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Riwayat Transaksi",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = PureWhite
                )
            )
        },
        containerColor = BackgroundLight,
        modifier = modifier
            .fillMaxSize()
            .testTag("transaksi_screen")
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Filter Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filters.forEach { filter ->
                    val isSelected = selectedFilter == filter
                    val label = when (filter) {
                        "Semua" -> "Semua"
                        "CRYPTO" -> "Crypto USDT"
                        "PPOB" -> "PPOB & PLN"
                        else -> "Top Up"
                    }
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) PrimaryBlue else PureWhite,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isSelected) PrimaryBlue else SoftBlueBorder
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { selectedFilter = filter }
                    ) {
                        Text(
                            text = label,
                            color = if (isSelected) PureWhite else TextSecondary,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // RecentTransactions list container
            RecentTransactions(
                items = filteredList,
                onItemClick = onItemClick,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
