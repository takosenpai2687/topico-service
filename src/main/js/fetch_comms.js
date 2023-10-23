bannerSpan = document.querySelector(
    ".ListingLayout-outerContainer > *:nth-child(2) > span:first-child"
);
style = bannerSpan.style.background || bannerSpan.style.backgroundImage;

bannerSrc = style.match(/url\(["']?(.*?)["']?\)/i)[1];

avatarEle = document.querySelector("header button img");
avatarSrc = avatarEle.src;

console.log(
    JSON.stringify({
        banner_url: bannerSrc,
        avatar_url: avatarSrc,
    })
);
