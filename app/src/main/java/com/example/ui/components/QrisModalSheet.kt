package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.IceBlue
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SecondaryBlue
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrisModalSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onPaymentSuccess: (amount: Long, merchantName: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFlashlightOn by remember { mutableStateOf(false) }

    // Animated scanning laser line
    val infiniteTransition = rememberInfiniteTransition(label = "qris_laser_transition")
    val laserOffsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 180f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "qris_laser_offset"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = PureWhite,
        modifier = modifier.testTag("qris_modal_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = PrimaryBlue,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "QR",
                                color = PureWhite,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        text = "Scan QRIS Pembayaran",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.testTag("qris_close_button")
                ) {
                    Icon(
                        imageVector = AppIcons.Close,
                        contentDescription = "Tutup",
                        tint = TextSecondary
                    )
                }
            }

            Text(
                text = "Arahkan kamera ke kode QRIS merchant untuk membayar otomatis",
                fontSize = 12.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
            )

            // Simulated Camera Scanner Viewfinder
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(DeepNavy)
                    .border(2.dp, PrimaryBlue, RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Background simulated grid
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = AppIcons.NavQris,
                        contentDescription = null,
                        tint = PureWhite.copy(alpha = 0.25f),
                        modifier = Modifier.size(90.dp)
                    )
                }

                // Laser Scanning Line
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (laserOffsetY - 90).dp)
                        .height(3.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    PrimaryBlue,
                                    SecondaryBlue,
                                    PrimaryBlue,
                                    Color.Transparent
                                )
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Actions: Flashlight & Gallery
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isFlashlightOn) PrimaryBlue else IceBlue,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { isFlashlightOn = !isFlashlightOn }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                        .testTag("qris_flashlight_button")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (isFlashlightOn) "💡 Flashlight Nyala" else "🔦 Flashlight",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isFlashlightOn) PureWhite else PrimaryBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = IceBlue,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { /* Pick from gallery */ }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                        .testTag("qris_gallery_button")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🖼️ Dari Galeri",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryBlue
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // Quick Demo Payment Button
            Button(
                onClick = {
                    onPaymentSuccess(25_000L, "Kopi Kenangan QRIS")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("qris_simulate_pay_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text(
                    text = "Simulasi Bayar QRIS • Rp 25.000",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PureWhite
                )
            }
        }
    }
}
