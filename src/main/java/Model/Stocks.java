package Model;


import yahoofinance.YahooFinance;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Stocks {

    //Variables
    private String tickerName;
    private int quantity;
    private double pricePaid;
    private BigDecimal currentPrice;
    private double PLValue;



//////////////////////////////////////////////////////////////////////////////////////

    //Constructor
    public Stocks(String name, int mquantity, double mpricePaid) throws IOException {

        tickerName = name;
        quantity = mquantity;
        pricePaid = mpricePaid;
        currentPrice = YahooFinance.get(name).getQuote().getPrice().setScale(2, RoundingMode.CEILING);
        PLValue =  (Math.round((currentPrice.doubleValue()-pricePaid) * 100.00) / 100.00) * mquantity;


    }

////////////////////////////////////////////////////////////////////////////////////////


    public String getTickerName() {
        return tickerName;
    }

    public void setTickerName(String tickerName) {
        this.tickerName = tickerName;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }



    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }



    public double getPLValue() {
        return PLValue;
    }

    public void setPLValue(double PLValue) {
        this.PLValue = PLValue;
    }


}
