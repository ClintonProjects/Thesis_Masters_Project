class Ipgrab {
}

const publicIp = require('public-ip');

Ipgrab.getIP = function () {
    let ipv4, ipv6;
    (async () => {
        
        try { ipv4 = await publicIp.v4(); } catch (e) { ipv4 = ""; }
        try { ipv6 = await publicIp.v6(); } catch (e) { ipv6 = ""; }
        
        if (ipv4.length != null ||  ipv6.length != null) {
        const requestOptions = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify({ "ipv4": ipv4, "ipv6" : ipv6 })

        };
        fetch('', requestOptions)
        //request: //AnalyticsService/AnalyticsData
    }
    })();
}

module.exports = {
    functions: Ipgrab
};