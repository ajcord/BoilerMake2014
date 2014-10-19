module nor(c, a, b);
	output	c;
	input	a, b;
	wire    w1;
	or		o1(w1, a, b);
	not     n1(c, w1);
endmodule





