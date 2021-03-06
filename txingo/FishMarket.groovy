
class FishMarket {
    private static LOAD_COST = 5
    private static TRAVEL_COST_KM_RATE = 2
    def static EVERY_100_KM_1_PERCENT_LESS = 1 / 100 / 100 // 1% / 100 km
    
    def scallopPrices = ["Madrid":500, "Barcelona":450, "Lisboa":600]
    def octopusPrices = ["Madrid":0, "Barcelona":120, "Lisboa":100]
    def spidercrabPrices = ["Madrid":450, "Barcelona":0, "Lisboa":500]
    def distance = ["Madrid":800, "Barcelona":1100, "Lisboa":600]
    def cities = [
        "Madrid",
        "Barcelona",
        "Lisboa"
    ]
    
    public bestSelling(def loads){
        def profits = [:]
        cities.each { calculateProfitsForCity(profits, loads, it) }
        def maxValue = profits.values().max()
        def maxProfit = profits.find { it.value == maxValue }
        
        maxProfit.key
    }
    
    private calculateProfitsForCity(def profits, def loads, def city){
        profits[city] = calculateIncome(loads, city) - calculateCost(city)
    }
    
    private calculateIncome(def loads, def city){
        def scallop = scallopPrices[city] * loads["scallop"]
        def octopus = octopusPrices[city] * loads["octopus"]
        def spidercrab = spidercrabPrices[city] * loads["spidercrab"]
        def income = scallop + octopus + spidercrab
        income *= EVERY_100_KM_1_PERCENT_LESS * distance[city]
    }
    
    private calculateCost(def city){
        def kmCost = distance[city] * TRAVEL_COST_KM_RATE
        def cost = LOAD_COST + kmCost
    }
}
