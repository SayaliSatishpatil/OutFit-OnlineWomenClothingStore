export const getNameById = (id, array = []) => {
  return array.filter((item) => item?.id === id)[0]?.name;
};

export const getIdByName = (name, array = []) => {
  console.log(array.filter((item) => item?.name === name)[0]?.id)
  return array.filter((item) => item?.name === name)[0]?.id || 0;
};