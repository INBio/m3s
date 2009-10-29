/*
*HIGHLIGHTER v.1.5 (080929)*
*�2005 Media Division (www.MediaDivision.com)*
*Written by Marius Smarandoiu & Armand Niculescu**/
function extractPageName(hrefString)
{
	var arr = hrefString.split('/');
	return  (arr.length<2) ? hrefString : arr[arr.length-2].toLowerCase() + arr[arr.length-1].toLowerCase();               
}

function setActiveMenu(arr, crtPage)
{
	for (var i=0; i<arr.length; i++)
	{
		if(extractPageName(arr[i].href) == crtPage)
		{
			if (arr[i].parentNode.tagName != "div")
			{
				arr[i].className = "item";
				arr[i].parentNode.className = "active-item";
			}
		}
	}
}

function setPage()
{
	hrefString = document.location.href ? document.location.href : document.location;
	if (document.getElementById("menu")!=null)
		setActiveMenu(document.getElementById("menu").getElementsByTagName("a"), extractPageName(hrefString));
}