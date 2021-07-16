export function setPlatform(state, isDesktop) {
  state.isDesktopPlatform = isDesktop;
}

export function setMenuActive(state, index) {
  console.log("setMenuActive", state, index);
  const keys = Object.keys(state.menus);
  state.activeMenu = keys[index];
}

export function setMenuActiveMenuName(state, menuName) {
  state.activeMenu = menuName;
}

export function setAccessToken(state, token) {
  state.accessToken = window.localStorage.setItem("accessToken", token);
}
