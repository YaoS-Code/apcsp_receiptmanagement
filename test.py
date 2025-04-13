import random
from datetime import datetime, timedelta

# Sample data for generating receipts
names = ["Hotpot", "Coffee Shop", "Bookstore", "Grocery Store", "Electronics", "Bakery", "Gas Station", "Pharmacy",
         "Clothing Store", "Cinema", "Fast Food", "Fine Dining", "Hardware Store", "Music Store", "Toy Store", 
         "Pet Store", "Gym", "Spa", "Salon", "Car Wash", "Furniture Store", "Art Gallery", "Museum Gift Shop", 
         "Bike Shop", "Florist", "Ice Cream Parlor", "Craft Store", "Sports Shop", "Tech Store", "Stationery"]

types = ["food", "beverage", "retail", "entertainment", "fuel", "health", "personal care", "fitness", "home", "art"]
status_options = ["completed", "pending", "cancelled", "refunded"]

# Start date for receipts
start_date = datetime(2025, 1, 1)

# Function to generate a single receipt as a formatted string
def generate_receipt(number):
    # Random date from the start date plus up to 90 days
    receipt_date = start_date + timedelta(days=random.randint(0, 90))
    # Cycle through names for variety
    name = names[(number - 1) % len(names)]
    r_type = random.choice(types)
    # For description, assume a quantity between 1 and 5
    description = random.randint(1, 5)
    # Generate amounts
    amount = round(random.uniform(5, 200), 2)
    tax = round(amount * random.uniform(0, 0.1), 2)
    tip = round(random.uniform(0, 50), 2)
    total = round(amount + tax + tip, 2)
    status = random.choice(status_options)
    
    receipt = (
        "---------Receipt---------\n"
        f"receiptNumber:{number}\n"
        f"receiptDate:{receipt_date.strftime('%Y-%m-%d')}\n"
        f"receiptName:{name}\n"
        f"receiptType:{r_type}\n"
        f"receiptDescription:{description}\n"
        f"receiptAmount:{amount}\n"
        f"receiptTaxAmount:{tax}\n"
        f"receiptTip:{tip}\n"
        f"receiptTotalAmount:{total}\n"
        f"receiptStatu:{status}\n"
        "-------------------------"
    )
    return receipt

# Generate and print 30 receipts
for i in range(1, 31):
    print(generate_receipt(i))
    print()  # extra newline for separation
