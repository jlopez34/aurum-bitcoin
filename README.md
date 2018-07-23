# Aurum-bitcoin
Services to research movements of the bitcoin flow (Summary and details)

## Development 

Bitcoin services were trying to solve two most issues, getting a summary trades during a period using as references this URL https://www.mercadobitcoin.net/api/BTC/trades/1501871369/1501891200/. That development was divided into two parts:

### BackEnd
Technologies: JDK 8, Spring Boot, Mockito (Testing)
#### Services:
##### -Summary:
Return an object that contains a resume about the movements of sell and buying in a period. The summary contains the following information: sales higher, buying higher, sale average, buying average, sale median, buying average, standard deviation sale and standard deviation buying.
http://localhost:8080/api/bitcoins/summary/

Example:
{
    "sales_higher": [
        {
            "date": 1501871382,
            "price": 9723,
            "amount": 0.002,
            "tid": 739718,
            "type": "sell"
        },
        {
            "date": 1501873191,
            "price": 9720,
            "amount": 0.1016883,
            "tid": 739854,
            "type": "sell"
        },
        {
            "date": 1501871402,
            "price": 9711,
            "amount": 0.0151344,
            "tid": 739725,
            "type": "sell"
        },
        {
            "date": 1501871402,
            "price": 9710,
            "amount": 0.1049356,
            "tid": 739726,
            "type": "sell"
        },
        {
            "date": 1501873191,
            "price": 9709.999,
            "amount": 0.0764017,
            "tid": 739855,
            "type": "sell"
        }
    ],
    "buyings_higher": [
        {
            "date": 1501871387,
            "price": 9738,
            "amount": 0.06385831,
            "tid": 739720,
            "type": "buy"
        },
        {
            "date": 1501871387,
            "price": 9735,
            "amount": 0.0177606,
            "tid": 739719,
            "type": "buy"
        },
        {
            "date": 1501871481,
            "price": 9730,
            "amount": 0.0107,
            "tid": 739732,
            "type": "buy"
        },
        {
            "date": 1501871581,
            "price": 9729,
            "amount": 0.00160949,
            "tid": 739749,
            "type": "buy"
        },
        {
            "date": 1501871657,
            "price": 9728,
            "amount": 0.01496585,
            "tid": 739751,
            "type": "buy"
        }
    ],
    "buyings_average": 9640.29,
    "sales_average": 9584.3545,
    "sale_median": 9593,
    "buying_median": 9650,
    "standard_desviation_sale": 60.463474,
    "standard_desviation_buying": 53.346184
}


##### -Trade by Id
Based on the results of the summary services, it will be possible getting the particular information, that was implemented by thinking in the UI, as follows an example:
http://localhost:8080/api/bitcoins/trade/{tid}

http://localhost:8080/api/bitcoins/trade/739725
{
    "date": 1501871402,
    "price": 9711,
    "amount": 0.0151344,
    "tid": 739725,
    "type": "sell"
}

### FrontEnd
Technologies:AngularJS@1.6.3

### Test Development
Using your preferred IDE (this project was developed using Eclipse), cloning this project in your desktop before that import that one using MVN tools, finally you could put running on the project 
