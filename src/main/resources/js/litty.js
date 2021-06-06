let countryAscending = false
let deathsAscending = false
let ratioAscending = false
function sortCountry() {
    let table, rows, switching, i, x, y, shouldSwitch;
    table = document.querySelector("#table-global");
    switching = true;
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 1; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].querySelectorAll("td")[0];
        y = rows[i + 1].querySelectorAll("td")[0];
        // Check if the two rows should switch place:
        if (countryAscending) {
            if (x.textContent.toLowerCase() > y.textContent.toLowerCase()) {
                // If so, mark as a switch and break the loop:
                shouldSwitch = true;
                break;
              }
        } else {
            if (x.textContent.toLowerCase() < y.textContent.toLowerCase()) {
                // If so, mark as a switch and break the loop:
                shouldSwitch = true;
                break;
              }
        }
        
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
    countryAscending = countryAscending ? false : true; 
  }
  function sortDeaths() {
    let table, rows, switching, i, x, y, shouldSwitch;
    table = document.querySelector("#table-global");
    switching = true;
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 1; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].querySelectorAll("td")[1];
        y = rows[i + 1].querySelectorAll("td")[1];
        // Check if the two rows should switch place:
        if (deathsAscending) {
            if (parseInt(x.textContent) > parseInt(y.textContent)) {
                // If so, mark as a switch and break the loop:
                shouldSwitch = true;
                break;
              }
        } else {
            if (parseInt(x.textContent) < parseInt(y.textContent)) {
                // If so, mark as a switch and break the loop:
                shouldSwitch = true;
                break;
              }
        }
        
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
    deathsAscending = deathsAscending ? false : true; 
  }

  function sortRatio() {
    let table, rows, switching, i, x, y, shouldSwitch;
    table = document.querySelector("#table-global");
    switching = true;
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 1; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].querySelectorAll("td")[2];
        y = rows[i + 1].querySelectorAll("td")[2];
        // Check if the two rows should switch place:
        if (ratioAscending) {
            if (parseInt(x.textContent) > parseInt(y.textContent)) {
                // If so, mark as a switch and break the loop:
                shouldSwitch = true;
                break;
              }
        } else {
            if (parseInt(x.textContent) < parseInt(y.textContent)) {
                // If so, mark as a switch and break the loop:
                shouldSwitch = true;
                break;
              }
        }
        
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
    ratioAscending = ratioAscending ? false : true; 
  }