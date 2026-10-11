package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.CryptoNetwork
import com.example.ui.components.AppIcons
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

private const val USDT_RATE = 16250.0

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BuyStablecoinScreen(
    currentRupiahBalance: Long,
    onBackClick: () -> Unit,
    onPurchaseSuccess: (amountRupiah: Long, amountUsdt: Double, network: CryptoNetwork, walletAddress: String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Hardware and custom back handler
    BackHandler { onBackClick() }

    var nominalInput by remember { mutableStateOf("500000") }
    var selectedNetwork by remember { mutableStateOf(CryptoNetwork.TRC20) }
    var walletAddressInput by remember { mutableStateOf("") }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    val nominalLong = nominalInput.filter { it.isDigit() }.toLongOrNull() ?: 0L
    val grossUsdt = if (nominalLong > 0) nominalLong / USDT_RATE else 0.0
    val netUsdt = (grossUsdt - selectedNetwork.feeUsdt).coerceAtLeast(0.0)

    val quickNominals = listOf(
        100_000L to "100rb",
        250_000L to "250rb",
        500_000L to "500rb",
        1_000_000L to "1 Juta",
        2_500_000L to "2.5 Juta",
        5_000_000L to "5 Juta"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.buy_stablecoin_title),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("buy_stablecoin_back_button")
                    ) {
                        Icon(
                            imageVector = AppIcons.Back,
                            contentDescription = "Kembali",
                            tint = TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = PureWhite
                )
            )
        },
        containerColor = BackgroundLight,
        modifier = modifier
            .fillMaxSize()
            .testTag("buy_stablecoin_screen")
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Live Exchange Rate Banner
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = IceBlueCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, SoftBlueBorder, RoundedCornerShape(16.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = UsdtGreen,
                            modifier = Modifier.size(24.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "₮",
                                    color = PureWhite,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Text(
                            text = "Kurs Real-Time",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                    }
                    Text(
                        text = "1 USDT = Rp 16.250",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Section 1: Nominal Rupiah
            Text(
                text = "1. " + stringResource(R.string.input_rupiah_label),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = nominalInput,
                onValueChange = { nominalInput = it.filter { ch -> ch.isDigit() } },
                prefix = {
                    Text(
                        text = "Rp ",
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue,
                        fontSize = 16.sp
                    )
                },
                placeholder = { Text("Contoh: 500000") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("nominal_rupiah_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = SoftBlueBorder,
                    focusedContainerColor = PureWhite,
                    unfocusedContainerColor = PureWhite
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Nominal Chips
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                quickNominals.forEach { (amount, label) ->
                    val isSelected = nominalLong == amount
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) PrimaryBlue else PureWhite,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isSelected) PrimaryBlue else SoftBlueBorder
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { nominalInput = amount.toString() }
                            .testTag("quick_nominal_chip_${label}")
                    ) {
                        Text(
                            text = label,
                            color = if (isSelected) PureWhite else TextPrimary,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Estimated Conversion Result Box
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Estimasi USDT Diterima",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                        Text(
                            text = "%,.2f USDT".format(netUsdt),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryBlue
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = IceBlue
                    ) {
                        Text(
                            text = "Gas Fee: ${selectedNetwork.feeUsdt} USDT",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryBlueDark,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // Section 2: Pilihan Jaringan (BEP-20, TRC-20, ERC-20, Polygon)
            Text(
                text = "2. " + stringResource(R.string.select_network_label),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "Pilih jaringan yang sesuai dengan alamat wallet tujuan Anda",
                fontSize = 12.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CryptoNetwork.entries.forEach { network ->
                    val isSelected = selectedNetwork == network
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isSelected) IceBlueCard else PureWhite,
                        border = androidx.compose.foundation.BorderStroke(
                            width = if (isSelected) 1.8.dp else 1.dp,
                            color = if (isSelected) PrimaryBlue else SoftBlueBorder
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { selectedNetwork = network }
                            .testTag("network_option_${network.id}")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { selectedNetwork = network },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = PrimaryBlue
                                )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = network.displayName,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "(${network.chainFullName})",
                                        fontSize = 11.sp,
                                        color = TextSecondary
                                    )
                                }
                                Text(
                                    text = "Estimasi: ${network.confirmationTime}",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Fee: ${network.feeUsdt} USDT",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (network == CryptoNetwork.ERC20) Color(0xFFEF4444) else SuccessGreen
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // Section 3: Field Alamat Crypto Wallet Tujuan
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "3. " + stringResource(R.string.wallet_address_label),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                // Tempel button for quick demo/testing
                Text(
                    text = "Tempel Contoh",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue,
                    modifier = Modifier
                        .clickable {
                            walletAddressInput = when (selectedNetwork) {
                                CryptoNetwork.TRC20 -> "TXq83nLKa72F4wY9mZ9k1aPqX89yBc2V1r"
                                else -> "0x71C836e520AB28eE87f71B3280036666874b8301"
                            }
                        }
                        .testTag("paste_wallet_button")
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = walletAddressInput,
                onValueChange = { walletAddressInput = it.trim() },
                placeholder = {
                    Text(
                        text = "Format: ${selectedNetwork.addressPrefixHint} (Alamat ${selectedNetwork.displayName})",
                        fontSize = 13.sp
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("wallet_address_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = SoftBlueBorder,
                    focusedContainerColor = PureWhite,
                    unfocusedContainerColor = PureWhite
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Safety Warning Box
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFFEF3C7),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚠️",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Pastikan alamat wallet tujuan mendukung jaringan ${selectedNetwork.displayName}. Aset yang dikirim ke jaringan yang salah tidak dapat dikembalikan.",
                        fontSize = 11.sp,
                        color = Color(0xFF92400E)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Section 4: Ringkasan Transaksi
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = PureWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.purchase_summary_title),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    SummaryRow("Nominal Pembelian", "Rp " + "%,d".format(nominalLong).replace(',', '.'))
                    SummaryRow("Kurs Konversi", "Rp 16.250 / USDT")
                    SummaryRow("Jaringan Terpilih", "${selectedNetwork.displayName} (${selectedNetwork.chainFullName})")
                    SummaryRow("Biaya Jaringan (Gas)", "${selectedNetwork.feeUsdt} USDT")

                    HorizontalDivider(
                        color = DividerColor,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total USDT Diterima",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "%,.2f USDT".format(netUsdt),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryBlue
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            val isFormValid = nominalLong >= 50_000L && walletAddressInput.isNotBlank() && nominalLong <= currentRupiahBalance

            // Continue Purchase Button
            Button(
                onClick = { showConfirmDialog = true },
                enabled = isFormValid,
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue,
                    disabledContainerColor = Color(0xFF94A3B8)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("continue_purchase_button")
            ) {
                Text(
                    text = if (nominalLong > currentRupiahBalance) "Saldo Rupiah Tidak Cukup" else stringResource(R.string.btn_continue_purchase),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PureWhite
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // Confirmation Dialog
    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = {
                Text(
                    text = "Konfirmasi Beli USDT",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Total Bayar: Rp %,d".format(nominalLong).replace(',', '.'))
                    Text("Jaringan: ${selectedNetwork.displayName}")
                    Text("Diterima: %,.2f USDT".format(netUsdt), fontWeight = FontWeight.Bold, color = PrimaryBlue)
                    Text("Wallet: ${walletAddressInput.take(10)}...${walletAddressInput.takeLast(8)}", fontSize = 12.sp, color = TextSecondary)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Apakah Anda yakin ingin memproses transaksi ini?", fontSize = 13.sp)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showConfirmDialog = false
                        onPurchaseSuccess(nominalLong, netUsdt, selectedNetwork, walletAddressInput)
                        showSuccessDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    modifier = Modifier.testTag("confirm_dialog_proceed_button")
                ) {
                    Text("Ya, Beli Sekarang")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showConfirmDialog = false },
                    modifier = Modifier.testTag("confirm_dialog_cancel_button")
                ) {
                    Text("Batal")
                }
            }
        )
    }

    // Success Dialog
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = {
                showSuccessDialog = false
                onBackClick()
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = CircleShape, color = SuccessGreen, modifier = Modifier.size(24.dp)) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("✓", color = PureWhite, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Pembelian Berhasil!", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Text(
                    "Transaksi Anda sebesar %,.2f USDT via jaringan ${selectedNetwork.displayName} telah berhasil diproses dan dikirimkan ke wallet Anda.".format(netUsdt)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        onBackClick()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    modifier = Modifier.testTag("success_dialog_close_button")
                ) {
                    Text("Kembali ke Dashboard")
                }
            }
        )
    }
}

@Composable
private fun SummaryRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 12.sp, color = TextSecondary)
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
    }
}
