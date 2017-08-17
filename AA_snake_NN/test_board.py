import numpy as np

def drawBoard(boards, num):
	for i in boards[num]:
		for j in i:
			if j == 0:
				print(" "),
			else:
				print(j),
	print("")


