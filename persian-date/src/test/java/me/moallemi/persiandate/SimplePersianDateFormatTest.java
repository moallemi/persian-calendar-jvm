package me.moallemi.persiandate;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SimplePersianDateFormatTest {

    //region Default pattern

    @Test
    public void testFormat1() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(9, 2, 3);
        assertEquals("0009/02/03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat2() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(9, 12, 3);
        assertEquals("0009/12/03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat3() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(99, 2, 3);
        assertEquals("0099/02/03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat4() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(99, 12, 3);
        assertEquals("0099/12/03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat5() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(399, 2, 3);
        assertEquals("0399/02/03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat6() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(399, 12, 3);
        assertEquals("0399/12/03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat7() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(1399, 2, 3);
        assertEquals("1399/02/03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat8() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(1399, 12, 3);
        assertEquals("1399/12/03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat9() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(9, 2, 23);
        assertEquals("0009/02/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat10() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(9, 12, 23);
        assertEquals("0009/12/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat11() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(399, 2, 23);
        assertEquals("0399/02/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat12() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(399, 12, 23);
        assertEquals("0399/12/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat13() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(1399, 2, 23);
        assertEquals("1399/02/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat14() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(99, 12, 23);
        assertEquals("0099/12/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat15() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(99, 2, 23);
        assertEquals("0099/02/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat16() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        PersianDate persianDate = PersianDate.of(1399, 12, 23);
        assertEquals("1399/12/23", simplePersianDateFormat.format(persianDate));
    }

    //endregion Default pattern

    //region  yy MM dd pattern

    @Test
    public void testFormat17() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(9, 2, 3);
        assertEquals("09 02 03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat18() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(9, 12, 3);
        assertEquals("09 12 03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat19() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(99, 2, 3);
        assertEquals("99 02 03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat20() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(99, 12, 3);
        assertEquals("99 12 03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat21() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(399, 2, 3);
        assertEquals("99 02 03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat22() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(399, 12, 3);
        assertEquals("99 12 03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat23() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(1399, 2, 3);
        assertEquals("99 02 03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat24() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(1399, 12, 3);
        assertEquals("99 12 03", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat25() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(9, 2, 23);
        assertEquals("09 02 23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat26() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(9, 12, 23);
        assertEquals("09 12 23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat27() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(399, 2, 23);
        assertEquals("99 02 23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat28() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(399, 12, 23);
        assertEquals("99 12 23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat29() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(1399, 2, 23);
        assertEquals("99 02 23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat30() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(99, 12, 23);
        assertEquals("99 12 23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat31() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(99, 2, 23);
        assertEquals("99 02 23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat32() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy MM dd");
        PersianDate persianDate = PersianDate.of(1399, 12, 23);
        assertEquals("99 12 23", simplePersianDateFormat.format(persianDate));
    }

    //endregion Default pattern

    //region yyyy/M/d

    @Test
    public void testFormat33() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(9, 2, 3);
        assertEquals("0009/2/3", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat34() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(9, 12, 3);
        assertEquals("0009/12/3", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat35() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(99, 2, 3);
        assertEquals("0099/2/3", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat36() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(99, 12, 3);
        assertEquals("0099/12/3", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat37() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(399, 2, 3);
        assertEquals("0399/2/3", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat38() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(399, 12, 3);
        assertEquals("0399/12/3", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat39() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(1399, 2, 3);
        assertEquals("1399/2/3", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat40() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(1399, 12, 3);
        assertEquals("1399/12/3", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat41() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(9, 2, 23);
        assertEquals("0009/2/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat42() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(9, 12, 23);
        assertEquals("0009/12/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat43() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(399, 2, 23);
        assertEquals("0399/2/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat44() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(399, 12, 23);
        assertEquals("0399/12/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat45() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(1399, 2, 23);
        assertEquals("1399/2/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat46() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(99, 12, 23);
        assertEquals("0099/12/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat47() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(99, 2, 23);
        assertEquals("0099/2/23", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testFormat48() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        PersianDate persianDate = PersianDate.of(1399, 12, 23);
        assertEquals("1399/12/23", simplePersianDateFormat.format(persianDate));
    }

    //endregion Default pattern

    @Test
    public void testFormat49() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/MMM/ddd");
        PersianDate persianDate = PersianDate.of(1399, 12, 23);
        assertEquals("1399/ESFAND/023", simplePersianDateFormat.format(persianDate));
    }

    @Test
    public void testParse1() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd yy");
        Date date = simpleDateFormat.parse("2014 04 23 15");
        System.out.println(date);

        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        assertEquals(PersianDate.of(1398, 6, 21), simplePersianDateFormat.parse("1398/06/21"));
    }

    @Test(expected = PersianDateException.class)
    public void testParse2() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat();
        assertEquals(PersianDate.of(1398, 6, 1), simplePersianDateFormat.parse("1398/6/1"));
    }

    @Test
    public void testParse3() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yyyy/M/d");
        assertEquals(PersianDate.of(1398, 6, 1), simplePersianDateFormat.parse("1398/6/1"));
    }

    @Test
    public void testParse4() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy/M/d");
        assertEquals(PersianDate.of(1398, 6, 1), simplePersianDateFormat.parse("98/6/1"));
    }

    @Test
    public void testParse5() {
        SimplePersianDateFormat simplePersianDateFormat = new SimplePersianDateFormat("yy M d MM yyyy dd");
        assertEquals(PersianDate.of(1324, 5, 23), simplePersianDateFormat.parse("98 6 1 05 1324 23"));
    }
}