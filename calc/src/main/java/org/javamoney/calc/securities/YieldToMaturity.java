/*
 * Copyright (c) 2012, 2018, Werner Keil, Anatole Tresch and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Contributors: @manuela-grindei
 */
package org.javamoney.calc.securities;


import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The yield to maturity formula is used to calculate the yield on a bond based on its current price on the market. The yield to maturity formula looks
 * at the effective yield of a bond based on compounding as opposed to the simple yield which is found using the dividend yield formula.
 *
 * @author Manuela Grindei
 * @see <a href="http://www.financeformulas.net/Yield_to_Maturity.html">http://www.financeformulas.net/Yield_to_Maturity.html</a>
 */
public class YieldToMaturity {

    /**
     * Private constructor.
     */
    private YieldToMaturity() {
    }

    /**
     * Calculates the yield to maturity.
     *
     * @param couponPaymentAmount     the coupon/interest payment
     * @param faceAmount              the face value
     * @param priceAmount             the price
     * @param numberOfYearsToMaturity the number of years to maturity
     * @return the yield to maturity
     */
    public static BigDecimal calculate(MonetaryAmount couponPaymentAmount, MonetaryAmount faceAmount, MonetaryAmount priceAmount, int numberOfYearsToMaturity) {
        final BigDecimal coupon = BigDecimal.valueOf(couponPaymentAmount.getNumber().doubleValueExact());
        final BigDecimal face = BigDecimal.valueOf(faceAmount.getNumber().doubleValueExact());
        final BigDecimal price = BigDecimal.valueOf(priceAmount.getNumber().doubleValueExact());

        final BigDecimal averagedDifference = face.subtract(price).divide(BigDecimal.valueOf(numberOfYearsToMaturity), MathContext.DECIMAL64);
        final BigDecimal averagePrice = face.add(price).divide(BigDecimal.valueOf(2), MathContext.DECIMAL64);

        return coupon.add(averagedDifference).divide(averagePrice, MathContext.DECIMAL64);
    }
}
