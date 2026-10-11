package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.TransactionHistoryItem
import com.example.ui.theme.CryptoTeal
import com.example.ui.theme.DividerColor
import com.example.ui.theme.IceBlue
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun RecentTransactions(
    items: List<TransactionHistoryItem>,
    onItemClick: (TransactionHistoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("recent_transactions_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.recent_transactions),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = stringResource(R.string.see_all),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryBlue,
                    modifier = Modifier.clickable { /* See all */ }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            items.forEachIndexed { index, item ->
                TransactionRowItem(
                    item = item,
                    onClick = { onItemClick(item) },
                    testTag = "transaction_item_${item.id}"
                )
                if (index < items.lastIndex) {
                    HorizontalDivider(
                        color = DividerColor.copy(alpha = 0.6f),
                        thickness = 0.8.dp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun TransactionRowItem(
    item: TransactionHistoryItem,
    onClick: () -> Unit,
    testTag: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp)
            .testTag(testTag),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Circle based on category
        val (bgColor, textColor, badge) = when (item.category) {
            "CRYPTO" -> Triple(IceBlue, PrimaryBlue, "₮")
            "PLN" -> Triple(Color(0xFFFEF9C3), Color(0xFFCA8A04), "⚡")
            "PULSA" -> Triple(Color(0xFFE0F2FE), Color(0xFF0284C7), "📱")
            else -> Triple(Color(0xFFF3E8FF), Color(0xFF9333EA), "📄")
        }

        Surface(
            shape = CircleShape,
            color = bgColor,
            modifier = Modifier.size(42.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = badge,
                    fontSize = 16.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Title and Subtitle
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${item.subtitle} • ${item.date}",
                fontSize = 11.sp,
                color = TextSecondary
            )
        }

        // Amount & Crypto Equivalent
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = (if (item.isPositive) "+" else "-") + item.amountRupiah,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (item.isPositive) SuccessGreen else TextPrimary
            )
            if (item.amountCrypto != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.amountCrypto,
                    fontSize = 11.sp,
                    color = PrimaryBlue,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
