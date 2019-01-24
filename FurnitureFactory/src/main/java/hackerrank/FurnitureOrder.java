package hackerrank;

import java.util.HashMap;
import java.util.stream.Collectors;

public class FurnitureOrder implements FurnitureOrderInterface {
    
	private final HashMap<Furniture, Integer> furnitures;

    /**
     * Initialize a new mapping of Furniture types to order quantities.
     */
    FurnitureOrder() {
        furnitures = new HashMap<Furniture, Integer>();
    }

    public void addToOrder(final Furniture type, final int furnitureCount) {
        Integer count = 0;
        if(furnitures.containsKey(type)) {
            count = furnitures.get(type);
        }
        furnitures.put(type, count + furnitureCount);
    }

    public HashMap<Furniture, Integer> getOrderedFurniture() {
        return new HashMap<Furniture, Integer>(furnitures);
    }

    public float getTotalOrderCost() {
        if(!furnitures.isEmpty()) {
        	return furnitures.entrySet().stream()
        			.map(f -> f.getKey().cost() * f.getValue())
        			.collect(Collectors.toList())
        			.stream()
        			.reduce(Float::sum)
        			.get();
        }
        return 0.0f;
    }

    public int getTypeCount(Furniture type) {
        if(furnitures.containsKey(type)) {
        	return furnitures.get(type);
        }
        return 0;
    }

    public float getTypeCost(Furniture type) {
    	if(furnitures.containsKey(type)) {
        	return furnitures.get(type) * type.cost();
        }
        return 0.0f;
    }

    public int getTotalOrderQuantity() {
    	if(!furnitures.isEmpty()) {
        	return furnitures.values().stream()
        			.reduce(Integer::sum)
        			.get();
        }
        return 0;
    }
}