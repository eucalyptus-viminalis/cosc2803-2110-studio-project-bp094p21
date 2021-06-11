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

  (function horizontalScrollingBanner() {
    var banners = document.getElementsByClassName('horizontal-scrolling-banner');
    if (!banners || banners.length === 0) {
      return;
    }
    var pxPerSecond = 50;
    setUpElements();
    scrollTheBanners();
    window.addEventListener('resize', setUpElements);
  
    function setUpElements() {
      for (var i = 0; i < banners.length; i++) {
        var currentBanner = banners[i];
        var helperWrapperClass = 'horizontal-scrolling-banner__helper-wrapper';
        var currentHelperWrapper = currentBanner.querySelector('.' + helperWrapperClass);
        if (currentHelperWrapper) {
          var clones = currentHelperWrapper.querySelectorAll('[data-clone]');
          Array.prototype.forEach.call(clones, function(clone) {
            clone.remove();
          });
          var childrenCount = currentHelperWrapper.children.length;
          for (var i = 0; i < childrenCount; i++) {
            currentBanner.appendChild(currentHelperWrapper.children[0]);
          }
          currentHelperWrapper.remove();
        }
  
        var children = currentBanner.children;
  
        var bannerWidth = currentBanner.getBoundingClientRect().width;
        var minWidthToCoverBanner = (bannerWidth * 2) + children[0].getBoundingClientRect().width;
        var childrenWidth = Array.prototype.reduce.call(children, function(r, child) {
          return r + child.getBoundingClientRect().width;
        }, 0);
        var currentWidth = childrenWidth;
  
  
        do {
          Array.prototype.forEach.call(children, function(child) {
            var clone = child.cloneNode();
            clone.setAttribute('aria-hidden', true);
            clone.dataset.clone = true;
            currentBanner.appendChild(clone);
          });
          currentWidth += childrenWidth;
        } while (currentWidth < minWidthToCoverBanner);
  
        var transitionHelperWrapper = document.createElement('div');
        transitionHelperWrapper.classList.add('horizontal-scrolling-banner__helper-wrapper');
        var childrenCount = children.length;
        for (var i = 0; i < childrenCount; i++) {
          transitionHelperWrapper.appendChild(children[0]);
        }
        currentBanner.appendChild(transitionHelperWrapper);
        transitionHelperWrapper.dataset.childrenWidth = childrenWidth;
      }
    }
  
    function scrollTheBanners() {
      for (var i = 0; i < banners.length; i++) {
        var helperWrapper = banners[i].firstElementChild;
        var childrenWidth = helperWrapper.dataset.childrenWidth;
        var offsetLeft = helperWrapper.offsetLeft;
  
        if (offsetLeft <= (childrenWidth * -1)) {
          helperWrapper.style.transitionDuration = '0s';
          helperWrapper.style.left = '0px';
          helperWrapper.style.removeProperty('transition-duration');
        } else if (helperWrapper.style.left === '' || helperWrapper.style.left === '0px') {
          setTimeout(function() {
            helperWrapper.style.transitionDuration = (childrenWidth / pxPerSecond).toFixed() + 's';
            helperWrapper.style.left = (childrenWidth * -1) + 'px';
          }, 0);
        }
      }
      requestAnimationFrame(scrollTheBanners);
    }
  })();