// import component
import React, { Component } from 'react';
import { View } from 'react-native';
import MultiSelect from 'react-native-multiple-select';

const items = [{
  name: 'Bike',
}, {
  name: 'Drive',
}, {
  name: 'Taxi',
}, {
  name: 'Transit',
}, {
  name: 'Uber',
}, {
  name: 'Walk',
}];

export default class TransportationPicker extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedItems: [],
    }
  };

  onSelectedItemsChange = selectedItems => {
    this.setState({ selectedItems });
    this.props.setTransportationFromParent(selectedItems);
  };

  render() {
    const { selectedItems } = this.state;
    return (
      <View style={{ flex: 1, padding: 5 }}>
        <MultiSelect
          hideTags
          items={items}
          uniqueKey="name"
          ref={(component) => { this.multiSelect = component }}
          onSelectedItemsChange={this.onSelectedItemsChange}
          selectedItems={selectedItems}
          selectText="Pick Transportation"
          searchInputPlaceholderText="Search Transportation Type..."
          onChangeInput={ (text)=> console.log(text)}
          altFontFamily="ProximaNova-Light"
          tagRemoveIconColor="#CCC"
          tagBorderColor="#CCC"
          tagTextColor="#CCC"
          selectedItemTextColor="#429ef5"
          selectedItemIconColor="#429ef5"
          itemTextColor="#000"
          displayKey="name"
          searchInputStyle={{ color: '#CCC' }}
          submitButtonColor="#429ef5"
          submitButtonText="Done"
        />
      </View>
    );
  }
}
