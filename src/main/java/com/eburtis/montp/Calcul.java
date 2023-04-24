package com.eburtis.montp;

public class Calcul {
    float a ;
    float b ;
    public Calcul(float a, float b) {
        this.a = a;
        this.b = b;
    }
    public float additionner(float a, float b) {
        return a + b;
    }
    public float soustraire(float a, float b) {
        return a - b;
    }
    public float multiplier(float a, float b) {
        return a * b;
    }
    public float diviser(float a, float b) throws Exception {
        if(b != 0){
            return a / b;
        }
        throw new Exception();
    }
    public float carre(float a) {
        return multiplier(a, a);
    }
    public float identiteRemarquable(float a, float b) {
        float a2 = multiplier(a, a);
        float b2 = multiplier(b, b);
        float ax2 = multiplier(2, a);
        return additionner(additionner(a2, multiplier(ax2, b)), b2);
    }

}
