import sys

# Do we want to read a filename from the cli with data, or we can just input the data using the input function for the milestone
# Then allow input with UI for final?
filepath = ''
args = sys.argv
args.pop(0)

while len(args) > 0:
    flag = args[0]
    args.pop(0)
    filepath = flag


longitude = 0
latitude = 0
population = 0

print("Enter Longitude:")
longitude = input()
print("Enter Latitude:")
latitude = input()
print("Enter Population:")
population = input()
# TODO: add checks for population, long, and lat values to make sure they are in reliable range
# Longitude (-180, 180), Latitude (-90, 90), Population (0, ?)

