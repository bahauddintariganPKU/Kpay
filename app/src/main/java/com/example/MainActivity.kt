package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.model.CryptoNetwork
import com.example.model.TransactionHistoryItem
import com.example.ui.screens.BuyStablecoinScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.theme.MyApplicationTheme

enum class AppDestination {
    DASHBOARD,
    BUY_STABLECOIN
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf(AppDestination.DASHBOARD) }

    var rupiahBalance by remember { mutableLongStateOf(15_850_000L) }
    var usdtBalance by remember { mutableDoubleStateOf(1_450.50) }

    var transactions by remember {
        mutableStateOf(
            listOf(
                TransactionHistoryItem(
                    id = "TX-01",
                    title = "Beli 50 USDT (TRC-20)",
                    subtitle = "Beli Stablecoin",
                    date = "Hari ini, 10:45",
                    amountRupiah = "Rp 812.500",
                    amountCrypto = "+50.00 USDT",
                    isPositive = false,
                    category = "CRYPTO"
                ),
                TransactionHistoryItem(
                    id = "TX-02",
                    title = "Token Listrik PLN 100k",
                    subtitle = "ID: 14285719283",
                    date = "Kemarin, 19:20",
                    amountRupiah = "Rp 101.500",
                    isPositive = false,
                    category = "PLN"
                ),
                TransactionHistoryItem(
                    id = "TX-03",
                    title = "Pulsa Telkomsel 50k",
                    subtitle = "081234567890",
                    date = "09 Okt 2026",
                    amountRupiah = "Rp 51.500",
                    isPositive = false,
                    category = "PULSA"
                ),
                TransactionHistoryItem(
                    id = "TX-04",
                    title = "Top Up Saldo Bank BCA",
                    subtitle = "Virtual Account",
                    date = "08 Okt 2026",
                    amountRupiah = "Rp 2.000.000",
                    isPositive = true,
                    category = "TOPUP"
                )
            )
        )
    }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "ScreenTransition"
    ) { screen ->
        when (screen) {
            AppDestination.DASHBOARD -> {
                DashboardScreen(
                    rupiahBalance = rupiahBalance,
                    usdtBalance = usdtBalance,
                    transactionList = transactions,
                    onBeliStablecoinClick = {
                        currentScreen = AppDestination.BUY_STABLECOIN
                    },
                    onPpobPurchase = { title, amount ->
                        if (rupiahBalance >= amount) {
                            rupiahBalance -= amount
                            val newTx = TransactionHistoryItem(
                                id = "TX-${System.currentTimeMillis() % 10000}",
                                title = title,
                                subtitle = "Pembayaran PPOB",
                                date = "Baru saja",
                                amountRupiah = "Rp " + "%,d".format(amount).replace(',', '.'),
                                isPositive = false,
                                category = if (title.contains("PLN")) "PLN" else "PULSA"
                            )
                            transactions = listOf(newTx) + transactions
                        }
                    },
                    onTopUpRupiah = { topUpAmount ->
                        rupiahBalance += topUpAmount
                        val newTx = TransactionHistoryItem(
                            id = "TX-${System.currentTimeMillis() % 10000}",
                            title = "Top Up Saldo",
                            subtitle = "Instan Top Up",
                            date = "Baru saja",
                            amountRupiah = "Rp " + "%,d".format(topUpAmount).replace(',', '.'),
                            isPositive = true,
                            category = "TOPUP"
                        )
                        transactions = listOf(newTx) + transactions
                    }
                )
            }

            AppDestination.BUY_STABLECOIN -> {
                BuyStablecoinScreen(
                    currentRupiahBalance = rupiahBalance,
                    onBackClick = {
                        currentScreen = AppDestination.DASHBOARD
                    },
                    onPurchaseSuccess = { amountRupiah, amountUsdt, network, walletAddress ->
                        rupiahBalance -= amountRupiah
                        usdtBalance += amountUsdt
                        val newTx = TransactionHistoryItem(
                            id = "TX-${System.currentTimeMillis() % 10000}",
                            title = "Beli %.2f USDT (%s)".format(amountUsdt, network.displayName),
                            subtitle = "Ke: ${walletAddress.take(6)}...${walletAddress.takeLast(4)}",
                            date = "Baru saja",
                            amountRupiah = "Rp " + "%,d".format(amountRupiah).replace(',', '.'),
                            amountCrypto = "+%.2f USDT".format(amountUsdt),
                            isPositive = false,
                            category = "CRYPTO"
                        )
                        transactions = listOf(newTx) + transactions
                    }
                )
            }
        }
    }
}
