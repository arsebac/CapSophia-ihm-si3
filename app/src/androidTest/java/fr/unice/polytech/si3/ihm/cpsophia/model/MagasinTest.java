package fr.unice.polytech.si3.ihm.cpsophia.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Francois Melkonian
 */
public class MagasinTest {
    @Test
    public void isType() throws Exception {
        Magasin exemple = new Magasin(1, "Sophia3000", Arrays.asList(MagasinType.ENFANT, MagasinType.HOMME), "3000 items pour vous");
        assertTrue(exemple.isType(MagasinType.HOMME));
        assertFalse(exemple.isType(MagasinType.FEMME));
    }

    @Test
    public void imageNotSet() throws Exception {
        Magasin exemple = new Magasin(1, "Sophia3000", Arrays.asList(MagasinType.ENFANT, MagasinType.HOMME), "3000 items pour vous");
        assertFalse(exemple.isHaveImage());
    }

    @Test
    public void imageSet() throws Exception {
        Magasin exemple = new Magasin(1, "Sophia3000", Arrays.asList(MagasinType.ENFANT, MagasinType.HOMME), "3000 items pour vous", android.support.design.R.drawable.abc_vector_test);
        assertTrue(exemple.isHaveImage());

    }
}