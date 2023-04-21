import re


latlong = re.compile('{{Mapframe\|(\d+\.?\d*)\|(\d+\.?\d*)')

# This multiline one would work but would require loading in an unknown number of lines from the file: 'pagebanner.*?\n(.*?)(?<!Understand)==(?!Understand)' with /s flag on

desc_title = re.compile('<title>(.*?)</title>')
desc_begin = re.compile('pagebanner')
desc_head = re.compile('==Understand==')
desc_end = re.compile('==(?!Understand)') #Matches any section heading except the 'Understand' heading that we want to include in the description.


add = False
outlist = []
title = ''


outfile = open("../data/data.csv", "w")
outfile.write('name,description,latitude,longitude')

try:
	datfile = open("../data/enwikivoyage-latest-pages-meta-current.xml", "r")

	for datline in datfile:
		dattext = datline.rstrip()

		#find title
		title_re = desc_title.search(dattext)
		if title_re:
			title = title_re.group(1)

		#skip understand heading
		head = desc_head.match(dattext)
		if head:
			continue
		
		#determine whether we have reached the end of a description
		end = desc_end.match(dattext)
		if end:
			add = False
			continue

		#Process line of a description
		if add:
			dattext = re.sub('[^\w.]+', " ", dattext) #remove punctuation other than periods
			dattext = re.sub('\.'," . ", dattext) #add spaces before periods
			dattext = dattext.lower()
			outlist.append(dattext)
			continue
	
		#determine whether we have reached the beginning of a description
		begin = desc_begin.search(dattext)
		if begin:
			#write previous entry (needs to be here in case there is an entry without latitude and longitude)
			outlist.append('\n')
			outtext = ''.join(outlist)
			outfile.write(outtext)
			print(outtext)
			outlist = []
			
			outlist.append(title)
			outlist.append(',')

			add = True
			continue

		#Check for Latitude and Longitude from map and append to output list (should always be outside description)
		pos = latlong.search(dattext)
		if pos:
			outlist.append(",")
			outlist.append(pos.group(1))
			outlist.append(",")
			outlist.append(pos.group(2))
			continue

	#write final entry (needs to be here since the last one will not yet be written)
	outlist.append('\n')
	outtext = ''.join(outlist)
	outfile.write(outtext)

	datfile.close()
	outfile.close()
except UnicodeDecodeError as ude:
	pass			


