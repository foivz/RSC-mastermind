$(function() {

    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            period: '2014 Q1',
            number: 453
            
            
        }, {
            period: '2014 Q2',
            number: 843
            
        }, {
            period: '2014 Q3',
            number: 832            
        }, {
            period: '2014 Q4',
            number: 432            
        }, {
            period: '2014 Q5',
            number: 574
            
        }, {
            period: '2014 Q6',
            number: 423
            
        }, {
            period: '2014 Q7',
            number: 456
            
        }, {
            period: '2014 Q8',
            number: 976
            
        }, {
            period: '2014 Q9',
            number: 423
            
        }, {
            period: '2014 Q10',
            number: 786
            
        }],
        xkey: 'period',
        ykeys: ['number'],
        labels: ['number'],
        pointSize: 2,
        hideHover: 'auto',
        lineColors: ['#E50000','#A60000','#008500'],
        resize: true
    });

    Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
            label: "A+",
            value: 12012
        }, {
            label: "A-",
            value: 30123
        }, {
            label: "B+",
            value: 20321
        },
        {
            label: "B-",
            value: 12213
        },
        {
            label: "AB+",
            value: 12321
        },
        {
            label: "AB-",
            value: 12411
        }],
        
        labelColor: '#0F0E0A',
        colors: [
                '#E33B3B','#688C7E','#E50000'    
  ],
        resize: true
    });

   

});
