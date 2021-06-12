document.querySelectorAll("table th").forEach(th => {
  th.addEventListener("click", () => {
    const table = th.parentElement.parentElement.parentElement;
    const thIndex = Array.prototype.indexOf.call(th.parentElement.children, th);
    console.log(thIndex);
    const isAsc = th.classList.contains("sorted-asc");
    console.log(isAsc);
    const dataType = th.dataset.type;
    sortTableByCol(table, thIndex, !isAsc, dataType);
  })
})
function sortTableByCol(table, thIndex, asc=true, dataType) {
    let tBody, sortDir, rows, sortedRows, columnIndex;
    columnIndex = thIndex + 1
    tBody = table.tBodies[0];
    sortDir = asc ? 1 : -1;
    rows = Array.from(tBody.querySelectorAll("tr"));
    sortedRows = rows.sort((a, b) => {
      const aData = a.querySelector(`td:nth-child(${columnIndex})`).textContent;
      const bData = b.querySelector(`td:nth-child(${columnIndex})`).textContent;
      if (dataType == "string") {
        return aData < bData ? (1 * sortDir) : (-1 * sortDir);
      } else if (dataType == "num") {
        return parseInt(aData) < parseInt (bData) ? (1 * sortDir) : (-1 * sortDir);
      }
    });
    while (tBody.firstchild) {
      tBody.removeChild(tBody.firstchild);
    }
    tBody.append(...sortedRows);
    table.querySelectorAll("th").forEach(th => th.classList.remove("sorted-asc", "sorted-desc"));
    table.querySelector(`th:nth-child(${columnIndex})`).classList.toggle('sorted-asc', asc);
    table.querySelector(`th:nth-child(${columnIndex})`).classList.toggle('sorted-desc', !asc);
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