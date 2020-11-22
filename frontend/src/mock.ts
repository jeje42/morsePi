import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import { Endpoint } from './config';

const getBlink = `${Endpoint.GetBlinkConfig}`;
const setBlink = `${Endpoint.SetBlinkConfig}`;

export const createMocks = () => {
  const mock = new MockAdapter(axios);

  mock
    .onGet(getBlink)
    .reply(200, {
      active: true,
      text: 'Hello there',
    })
    .onGet(setBlink)
    .reply(200, {});
};
