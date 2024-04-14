package com.company.Product.Filter;

import com.company.Product.constants.Wrapping;

public class StringFilter {
    @Override
    public boolean equals(Object other) {
        return Wrapping.CARDBOARD.equals(other) || Wrapping.GLASS.equals(other) || Wrapping.PLASTIC.equals(other);
    }

}


