set xlabel "Número de etiquetas"
set ylabel "Número de slots"
set monochrome
set key vertical top left
set grid
set pointsize 2
plot "LowerBound-64-1100-10-10000.txt" u 1:2 t 'LowerBound' w lines, \
