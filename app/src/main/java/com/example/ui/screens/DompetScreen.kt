package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BackgroundLight
import com.example.ui.theme.DividerColor
import com.example.ui.theme.IceBlue
import com.example.ui.theme.IceBlueCard
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.PrimaryBlueDark
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftBlueBorder
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.UsdtGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DompetScreen(
    rupiahBalance: Long,
    usdtBalance: Double,
    onTopUpClick: () -> Unit,
    onBeliCryptoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Dompet Saya",
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
            .testTag("dompet_screen")
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Card Saldo Rupiah
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(shape = CircleShape, color = IceBlue, modifier = Modifier.size(32.dp)) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text("Rp", fontWeight = FontWeight.Bold, color = PrimaryBlue, fontSize = 13.sp)
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Saldo Tunai IDR", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextPrimary)
                        }

                        Surface(shape = RoundedCornerShape(8.dp), color = IceBlueCard) {
                            Text(
                                "Aktif",
                                color = PrimaryBlue,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("Total Saldo", fontSize = 12.sp, color = TextSecondary)
                    Text(
                        text = "Rp " + "%,d".format(rupiahBalance).replace(',', '.'),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryBlue
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = DividerColor)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onTopUpClick,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                        ) {
                            Text("Top Up Saldo", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = { /* Withdraw */ },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Tarik Tunai", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = PrimaryBlue)
                        }
                    }
                }
            }

            // Card Saldo Crypto USDT
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(shape = CircleShape, color = UsdtGreen, modifier = Modifier.size(32.dp)) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text("₮", fontWeight = FontWeight.Bold, color = PureWhite, fontSize = 18.sp)
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Dompet Tether (USDT)", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextPrimary)
                        }

                        Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFDCFCE7)) {
                            Text(
                                "Multi-Chain",
                                color = SuccessGreen,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("Total Aset Crypto", fontSize = 12.sp, color = TextSecondary)
                    Text(
                        text = "%,.2f USDT".format(usdtBalance),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary
                    )
                    Text(
                        text = "≈ Rp " + "%,d".format((usdtBalance * 16250).toLong()).replace(',', '.'),
                        fontSize = 13.sp,
                        color = PrimaryBlue,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = DividerColor)
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onBeliCryptoClick,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                    ) {
                        Text("Beli Stablecoin Sekarang", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Linked Bank Accounts & E-Wallets
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text("Metode Pembayaran Terhubung", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextPrimary)
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🏦", fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Bank BCA", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = TextPrimary)
                                Text("8829102839 • Budi Pratama", fontSize = 11.sp, color = TextSecondary)
                            }
                        }
                        Text("Utama", color = PrimaryBlue, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = DividerColor)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("📱", fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("GoPay / ShopeePay", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = TextPrimary)
                                Text("081234567890", fontSize = 11.sp, color = TextSecondary)
                            }
                        }
                        Text("Terhubung", color = SuccessGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
