import csv
# Tables
record_table = {
    "Date": []
}
country_table = {
    "ID": [],
    "Name": [],
    "Latitude": [],
    "Longitude": [],
    "Population": [],
}
state_table = {
    "ID": [],
    "Name": [],
    "Latitude": [],
    "Longitude": [],
    "Population": [],
    "Country_RegionID": [],
}
country_record_deaths = {
    "Country_RegionID": [],
    "Date": [],
    "NewDeaths": [],
}
state_record_deaths = {
    "Province_StateID": [],
    "Date": [],
    "NewDeaths": [],
}
country_record_cases = {
    "Country_RegionID": [],
    "Date": [],
    "NewCases": [],
}
state_record_cases = {
    "Province_StateID": [],
    "Date": [],
    "NewCases": [],
}
deaths_fields = set()

with open("new_deaths.csv", "r") as file:
    reader = csv.DictReader(file)
    deaths_fields = reader.fieldnames
    for field in deaths_fields:
        if ("/" in field):
            record_table["Date"].append(field)
    counter_state = 0
    counter_country = 0
    for row in reader:
        country_name = row["Country_Region"]
        country_id = counter_country
        state_name = row["Province_State"]
        state_id = counter_state
        if (row["Country_Region"].strip() not in country_table["Name"]):
            country_table["ID"].append(country_id)
            country_table["Name"].append(row["Country_Region"].strip())
            if row["Province_State"].strip():
                country_table["Latitude"].append(None)
                country_table["Longitude"].append(None)
                country_table["Population"].append(None)
            else:    
                country_table["Latitude"].append(row["Latitude"])
                country_table["Longitude"].append(row["Longitude"])
                country_table["Population"].append(row["Population"])
                # Add death records to country_record_deaths
                for date in record_table["Date"]:
                    country_record_deaths["Country_RegionID"].append(country_id)
                    country_record_deaths["Date"].append(date)
                    country_record_deaths["NewDeaths"].append(row[date])
            counter_country += 1
        if row["Province_State"].strip():
            if (row["Province_State"].strip() not in state_table["Name"]):
                state_table["ID"].append(state_id)
                state_table["Name"].append(row["Province_State"].strip())
                state_table["Latitude"].append(row["Latitude"])
                state_table["Longitude"].append(row["Longitude"])
                state_table["Population"].append(row["Population"])
                state_table["Country_RegionID"].append(country_table["Name"].index(row["Country_Region"]))
            # Add death records to state_record_deaths
                for date in record_table["Date"]:
                    state_record_deaths["Province_StateID"].append(state_id)
                    state_record_deaths["Date"].append(date)
                    state_record_deaths["NewDeaths"].append(row[date])
                counter_state += 1
        # else:    
        #     country_table["Latitude"].append(row["Latitude"])
        #     country_table["Longitude"].append(row["Longitude"])
        #     country_table["Population"].append(row["Population"])
        #     # Add death records to country_record_deaths
        #     for date in record_table["Date"]:
        #         country_record_deaths["Country_RegionID"].append(country_id)
        #         country_record_deaths["Date"].append(date)
        #         country_record_deaths["NewDeaths"].append(row[date])

print("Fields - new_deaths.csv: " + str(len(deaths_fields)))
print("Unique dates: " + str(len(record_table["Date"])))

num_state_deaths = len(state_record_deaths["Date"])
num_country_deaths = len(country_record_deaths["Date"])
print("Num Country Deaths Records: " + str(num_country_deaths))
print("Num State Deaths Records: " + str(num_state_deaths))

with open("new_cases.csv", "r") as file:
    reader = csv.DictReader(file)
    counter_state = 0
    counter_country = 0
    for row in reader:
        if row["Province_State"]:
            # Add death records to state_record_cases
            for date in record_table["Date"]:
                state_record_cases["Province_StateID"].append(counter_state)
                state_record_cases["Date"].append(date)
                state_record_cases["NewCases"].append(row[date])
            counter_state += 1
        else:
            # Add death records to country_record_deaths
            for date in record_table["Date"]:
                country_record_cases["Country_RegionID"].append(counter_country)
                country_record_cases["Date"].append(date)
                country_record_cases["NewCases"].append(row[date])
            counter_country += 1

num_state_cases = len(state_record_cases["Date"])
num_country_cases = len(country_record_cases["Date"])
print("Num Country Cases Records: " + str(num_country_cases))
print("Num State Cases Records: " + str(num_state_cases))

# print("\nCountry Record Deaths:")
# for i in range(len(country_record_deaths["Date"])):
#     for col in country_record_deaths:
#         print(col + ": " + str(country_record_deaths[col][i]))
# print("\nState Record Deaths:")
# for i in range(len(state_record_deaths["Date"])):
#     for col in state_record_deaths:
#         print(col + ": " + str(state_record_deaths[col][i]))
# print("\nCountry Record Cases:")
# for i in range(len(country_record_cases["Date"])):
#     for col in country_record_cases:
#         print(col + ": " + str(country_record_cases[col][i]))
# print("\nCases Record Cases:")
# for i in range(len(state_record_cases["Date"])):
#     for col in state_record_cases:
#         print(col + ": " + str(state_record_cases[col][i]))

with open('record.csv', 'w') as file:
    writer = csv.writer(file)
    writer.writerow(["Date"])
    for date in record_table["Date"]:
        writer.writerow([date])

with open('country.csv', 'w') as file:
    writer = csv.writer(file)
    writer.writerow(["ID", "Name", "Latitude", "Longitude", "Population"])
    for val in country_table["ID"]:
        writer.writerow([country_table["ID"][val], country_table["Name"][val], country_table["Latitude"][val], country_table["Longitude"][val], country_table["Population"][val]])

with open('state.csv', 'w') as file:
    writer = csv.writer(file)
    writer.writerow(["ID", "Name", "Latitude", "Longitude", "Population", "Country_RegionID"])
    for val in state_table["ID"]:
        writer.writerow([state_table["ID"][val], state_table["Name"][val], state_table["Latitude"][val], state_table["Longitude"][val], state_table["Population"][val], state_table["Country_RegionID"][val]])

with open('country_deaths.csv', 'w') as file:
    writer = csv.writer(file)
    writer.writerow(["Country_RegionID", "Date", "NewDeaths"])
    for val in range(num_country_deaths):
        writer.writerow([country_record_deaths["Country_RegionID"][val], country_record_deaths["Date"][val], country_record_deaths["NewDeaths"][val]])

with open('state_deaths.csv', 'w') as file:
    writer = csv.writer(file)
    writer.writerow(["Province_StateID", "Date", "NewDeaths"])
    for val in range(num_state_deaths):
        writer.writerow([state_record_deaths["Province_StateID"][val], state_record_deaths["Date"][val], state_record_deaths["NewDeaths"][val]])

with open('country_cases.csv', 'w') as file:
    writer = csv.writer(file)
    writer.writerow(["Country_RegionID", "Date", "NewCases"])
    for val in range(num_country_cases):
        writer.writerow([country_record_cases["Country_RegionID"][val], country_record_cases["Date"][val], country_record_cases["NewCases"][val]])

with open('state_cases.csv', 'w') as file:
    writer = csv.writer(file)
    writer.writerow(["Province_StateID", "Date", "NewCases"])
    for val in range(num_state_cases):
        writer.writerow([state_record_cases["Province_StateID"][val], state_record_cases["Date"][val], state_record_cases["NewCases"][val]])



# countries = sorted(countries)

# print("Number of countries: " + str(len(countries)))
# print("Number of states: " + str(len(states)))


# with open('eggs.csv', 'w', newline='') as csvfile:
#     spamwriter = csv.writer(csvfile, delimiter=' ',
#                             quotechar='|', quoting=csv.QUOTE_MINIMAL)
#     spamwriter.writerow(['Spam'] * 5 + ['Baked Beans'])
#     spamwriter.writerow(['Spam', 'Lovely Spam', 'Wonderful Spam'])